package com.mattyeh.memberSystem.repository;

import com.mattyeh.memberSystem.entity.MemberEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends CrudRepository<MemberEntity, Integer> {
    List<MemberEntity> findMemberEntityByEmail(String email);
    List<MemberEntity> findMemberEntityByMemberName(String memberName);
}
