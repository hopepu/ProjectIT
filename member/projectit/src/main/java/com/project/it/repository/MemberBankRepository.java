package com.project.it.repository;

import com.project.it.domain.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberBankRepository extends JpaRepository<Bank, String> {
}
