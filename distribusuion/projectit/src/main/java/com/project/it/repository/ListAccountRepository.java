package com.project.it.repository;

import com.project.it.domain.ListAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ListAccountRepository extends JpaRepository<ListAccount, Long> {
    //조회(경로+계정)
    @EntityGraph(attributePaths = "accountObjectList")
    @Query("select site from ListAccount site where site.siNum=:siNum")
    Optional<ListAccount> selectOne(@Param("siNum") Long siNum);

    //목록(페이징)
    @EntityGraph(attributePaths = "accountObjectList") //조인처리(계정리스트 AccountObject)
    @Query("select site from ListAccount site  ")
    Page<ListAccount> accountList(Pageable pageable);

    //사용안함 처리
    @Modifying
    @Query("update ListAccount ac set ac.useState = :state where ac.siNum=:siNum")
    void updateDelState(@Param("siNum") Long siNum, @Param("state") boolean state);

    //삭제
    @Query("delete from ListAccount ac where ac.siNum=:siNum")
    void deleteByAccountList(Long siNum);

}
