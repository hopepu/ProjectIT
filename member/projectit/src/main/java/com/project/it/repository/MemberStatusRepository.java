package com.project.it.repository;

import com.project.it.domain.Member;
import com.project.it.domain.MemberRole;
import com.project.it.domain.MemberStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberStatusRepository extends JpaRepository<MemberStatus, String> {

    MemberStatus findByMemberMno(Long mno);

    @Query("select ms from MemberStatus ms order by ms.mno asc")
    Page<MemberStatus> selectList(Pageable pageable);

    @Query("select ms from MemberStatus ms WHERE ms.name LIKE %:searchQuery% order by ms.mno asc")
    Page<MemberStatus> searchByQuery(String searchQuery, Pageable pageable);

    MemberStatus searchByMno(Long mno);


    int countByTel(String tel);

    MemberStatus findByMno(Long mno);
}