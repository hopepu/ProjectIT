package com.project.it.repository;

import com.project.it.domain.Bank;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank,String> {

    @EntityGraph(attributePaths = {"bankCodeList"})
    Bank findByMemberMno(Long mno);
}
