package com.project.it.service;

import com.project.it.dto.*;

import java.util.List;

public interface AssetLicenseService {
    //C : 등록
    Long register(AssetLicenseIPDTO assetLicenseIPDTO);

    //R_one : 라이선스 정보 하나만 가져오기 + file 리스트
    AssetLicenseOneDTO getOne(Long ano);

    //R_all : 라이선스(asset) 리스트+ file 개수
    PageResponseDTO<AssetLicenseListOPDTO> getList(PageRequestDTO pageRequestDTO);
    //U : 라이선스 관리 정보 변경
    void update(AssetLicenseIPDTO assetLicenseIPDTO);

    //D: 라이선스 삭제_삭제처리(리스트에서 보이지 않게)
    void remove(Long ano);

    //D_forever : 라이선스 영구삭제_db에서 삭제
    void removeForever(Long ano);

    //R_all : 라이선스(asset) 리스트+ file 개수-noPaging
    List<AssetLicenseListOPDTO> getOnlyList();

}
