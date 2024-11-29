package com.project.it.service;

import com.project.it.dto.InfoComputerDTO;
import com.project.it.dto.PageRequestDTO;
import com.project.it.dto.PageResponseDTO;
import org.springframework.data.domain.Page;

public interface InfoComputerService {
    //등록
    Long register(InfoComputerDTO infoComputerDTO);
    //조회-one
    InfoComputerDTO getOne(Long cino);
    //조회-list
    PageResponseDTO<InfoComputerDTO> getList(PageRequestDTO pageRequestDTO);
    //변경  //단순정보로 수정X, 삭제후 재등록

    //삭제
    void remove(Long cino);
}
