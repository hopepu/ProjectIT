package com.project.it.repository;

import com.project.it.domain.AssetComputer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AssetComputerRepository extends JpaRepository<AssetComputer, Long> {
    //리스트
    @Query("select com from AssetComputer com where com.deleteOrNot=false ")
    Page<AssetComputer> searchAllWithPaging(Pageable pageable);

    //삭제처리
    @Modifying
    @Query("update  AssetComputer com set com.deleteOrNot=true where com.cno=:cno")
    void deleteUpdate(@Param("cno") Long cno);
}
