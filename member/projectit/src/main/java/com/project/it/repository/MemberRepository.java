package com.project.it.repository;

import com.project.it.domain.Member;
import com.project.it.domain.MemberRole;
import com.project.it.domain.MemberStatus;
import com.project.it.domain.OrganizationTeam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member,String> {


    @Query("select m from Member m where m.email = :email")
    Member getWithRoles(@Param("email") String email);

    @EntityGraph(attributePaths = {"memberRoleList"})
    Member searchMemberByMno(@Param("mno") Long mno);

    @Query("Select m from Member m join m.memberRoleList mr where mr = :searchQuery")
    List<Member> searchMembersByRole(MemberRole searchQuery);

    @Query("Select m from Member m join m.memberRoleList mr where mr = :searchQuery")
    Page<MemberStatus> searchByQuery(MemberRole searchQuery, Pageable pageable);


    int countByEmail(String email);
}
