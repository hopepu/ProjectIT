package com.project.it.service;

import com.project.it.dto.InfoLicenseIPDTO;
import com.project.it.dto.InfoLicenseOPDTO;
import com.project.it.dto.PageRequestDTO;
import com.project.it.dto.PageResponseDTO;

import java.util.List;

public interface InfoLicenseService {
    //C : 등록
    Long register(InfoLicenseIPDTO infoLicenseIPDTO);
    
    //R_one : 라이선스 정보 하나만 가져오기
    InfoLicenseOPDTO getOne(Long lno);
    
    //R_all : 라이선스 리스트(정보 객체만 담은 리스트-> asset file 과 합쳐져야 함)
    List<InfoLicenseOPDTO> getList();

    //R_all : 라이선스 리스트+paging
    PageResponseDTO<InfoLicenseOPDTO> getListWithPage(PageRequestDTO pageRequestDTO);

    //U : 라이선스 정보 변경
    void update(InfoLicenseIPDTO infoLicenseIPDTO);
    
    //D: 라이선스 삭제_삭제처리(리스트에서 보이지 않게)
    void remove(Long lno);

    //D_forever : 라이선스 영구삭제_db에서 삭제
    void removeForever(Long lno);
}
