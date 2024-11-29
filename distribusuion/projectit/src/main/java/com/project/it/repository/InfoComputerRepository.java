package com.project.it.repository;

import com.project.it.domain.InfoComputer;
import com.project.it.dto.InfoComputerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InfoComputerRepository extends JpaRepository<InfoComputer, Long> {
    //페이징용 리스트
    @Query("select i from InfoComputer i ")
    Page<InfoComputer> searchAll(Pageable pageable);

}
