package com.project.it.service;

import com.project.it.dto.InfoPartnersDTO;
import com.project.it.dto.PageRequestDTO;
import com.project.it.dto.PageResponseDTO;
import org.springframework.data.domain.Page;

public interface InfoPartnersService { //고객사 정보 관리 서비스
    //C: 고객사 등록
    Long register(InfoPartnersDTO infoPartnersDTO);
    //R_one: 고객사 정보 불러오기
    InfoPartnersDTO getOne(InfoPartnersDTO infoPartnersDTO); //회사명+phone검색
    //R_all : 고객사 리스트 불러오기
    PageResponseDTO<InfoPartnersDTO> getList(PageRequestDTO pageRequestDTO);
    //U : 고객사 정보 변경
    void update(InfoPartnersDTO infoPartnersDTO);
    //D : 고객사 삭제
    void remove(Long cno);
}
