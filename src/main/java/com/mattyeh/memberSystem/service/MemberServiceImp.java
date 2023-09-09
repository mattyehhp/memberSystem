package com.mattyeh.memberSystem.service;

import com.mattyeh.memberSystem.entity.MemberEntity;
import com.mattyeh.memberSystem.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImp implements MemberService {
    @Autowired
    private MemberRepository memberRepository;
    @Override
    public void createMember(MemberEntity member) {
        memberRepository.save(member);
    }

    @Override
    public MemberEntity getMemberByEmail(String email) {
        List<MemberEntity> memberList = memberRepository.findMemberEntityByEmail(email);
        MemberEntity member = new MemberEntity();
        if (memberList.size() > 0) {
            member = memberList.get(0);
        }
        return member;
    }

    @Override
    public boolean isEmailUsed(String email) {
        boolean result = false;
        List<MemberEntity> memberList = memberRepository.findMemberEntityByEmail(email);
        if (memberList.size() > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean isMemberNameUsed(String memberName) {
        boolean result = false;
        List<MemberEntity> memberList = memberRepository.findMemberEntityByMemberName(memberName);
        if (memberList.size() > 0) {
            result = true;
        }
        return result;
    }
}
