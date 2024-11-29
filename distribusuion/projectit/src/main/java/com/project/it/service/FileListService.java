package com.project.it.service;

import com.project.it.dto.FileUploadDTO;
import com.project.it.dto.PageRequestDTO;
import com.project.it.dto.PageResponseDTO;

import java.util.List;

public interface FileListService {
    //전체 조회(Paging)
    PageResponseDTO<FileUploadDTO> getFileList(PageRequestDTO pageRequestDTO);

    //전체 조회(Paging)+삭제여부
    PageResponseDTO<FileUploadDTO> searchFileList(PageRequestDTO pageRequestDTO, boolean state);


    //삭제조건 변경(복구)
    int restore(List<Long> fileNums);
    //영구삭제
    int deleteForever(List<Long> fileNums);

}
