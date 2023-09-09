package com.mattyeh.memberSystem.service;

import com.mattyeh.memberSystem.entity.MemberEntity;

import java.util.List;

public interface MemberService {
    void createMember(MemberEntity member);
    MemberEntity getMemberByEmail(String email);
    boolean isEmailUsed(String email);
    boolean isMemberNameUsed(String memberName);
}
