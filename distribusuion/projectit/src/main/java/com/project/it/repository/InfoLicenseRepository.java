package com.project.it.repository;

import com.project.it.domain.InfoLicense;

import com.project.it.dto.PageRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface InfoLicenseRepository extends JpaRepository<InfoLicense, Long> {
    //삭제처리
    @Modifying
    @Query("update InfoLicense lc set lc.deleteOrNot = :state where lc.lno=:lno")
    void updateDelState(@Param("lno") Long lno, @Param("state") boolean deleteOrNot);

    //페이징용 리스트
    @Query("select i from InfoLicense i where i.deleteOrNot=false ")
    Page<InfoLicense> searchAllByPaging(Pageable pageable);


}
