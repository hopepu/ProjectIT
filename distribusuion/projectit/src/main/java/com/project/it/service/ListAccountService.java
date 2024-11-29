package com.project.it.service;

import com.project.it.dto.*;

import java.util.List;

public interface ListAccountService {
    //등록-no Asset
    Long register(ListAccountIPDTO listAccountIPDTO);

    //등록-Asset
    Long registerAsset(ListAccountIPDTO listAccountIPDTO);

    //조회 All
    PageResponseDTO<ListAccountOPDTO> getList(PageRequestDTO pageRequestDTO);
    //조회 One
    ListAccountOPDTO getOne(Long siNum);
    //변경(사이트 주소, 인증여부)
    void modify(ListAccountIPDTO listAccountIPDTO);
    //변경(계정변경)
    void modifyAccount(List<AccountObjectIPDTO> accounts, Long siNum);

    //삭제(사용안함)
    void remove(Long siNum);
    //삭제(데이터 삭제)
    void removeForever(Long siNum);

}
