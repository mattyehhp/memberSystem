package com.mattyeh.memberSystem.service;

import com.mattyeh.memberSystem.entity.MemberEntity;


public interface MemberService {
    void createMember(MemberEntity member);
    MemberEntity getMemberByEmail(String email);
    MemberEntity getMemberByMemberName(String memberName);
    boolean isEmailUsed(String email);
    boolean isMemberNameUsed(String memberName);
}
