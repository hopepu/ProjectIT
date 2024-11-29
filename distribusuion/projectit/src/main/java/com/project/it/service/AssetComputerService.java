package com.project.it.service;

import com.project.it.dto.*;

import java.util.List;

public interface AssetComputerService {
    //등록
    Long register(AssetComputerDTO assetComputerDTO);
    //조회-개별(파일+info)
    AssetComputerOneDTO getOne(Long cno);
    //조회-리스트(+파일개수+info)
    PageResponseDTO<AssetComputerOneDTO> getList(PageRequestDTO pageRequestDTO);
    //변경
    void modify(AssetComputerDTO assetComputerDTO);
    //삭제처리
    void remove(Long cno);
    //삭제
    void removeForever(Long cno);

    //리스트-nopaging
    List<AssetComputerOneDTO> getOnlyList();
    
    
    
}
