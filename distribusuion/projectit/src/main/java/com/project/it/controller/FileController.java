package com.project.it.controller;

import com.project.it.dto.FileUploadDTO;
import com.project.it.dto.PageRequestDTO;
import com.project.it.dto.PageResponseDTO;
import com.project.it.service.FileListService;
import com.project.it.util.CustomFileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequestMapping("/dist/file")
@RequiredArgsConstructor
public class FileController { //file 다운로드 요청 처리
    private final CustomFileUtil fileUtil;
    private final FileListService fileListService;

    @GetMapping("/{path}/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("path") String path, @PathVariable("filename") String filename){
        log.info("fileController 실행+++++++++++++ : "+path+"/"+filename);
        String savePath = path.replace("_", File.separator);
        FileUploadDTO dto = new FileUploadDTO();
        dto.setFolderPath(savePath);
        dto.setSaveFileName(filename);
        return fileUtil.getFile(dto);
    }

    //파일전체 (삭제여부 검색)
   @GetMapping("/all/{state}")
    public PageResponseDTO<FileUploadDTO> fileSearchList(PageRequestDTO pageRequestDTO, @PathVariable("state")boolean state){
        return fileListService.searchFileList(pageRequestDTO, state);
   }

    //파일전체 (삭제여부 검색)
    @GetMapping("/all")
    public PageResponseDTO<FileUploadDTO> fileAllList(PageRequestDTO pageRequestDTO){
        return fileListService.getFileList(pageRequestDTO);
    }

    //파일 리스트 복구
    @PutMapping("/restore")
    public Map<String, Integer> restoreFiles(List<Long> fileList){
        int resulCnt =fileListService.restore(fileList);
        return Map.of("ResultCount", resulCnt);

    }

    //파일 영구삭제(DB)
    @DeleteMapping("/remove")
    public Map<String, Integer> removeFiles(List<Long> fileList){
        int resulCnt =fileListService.restore(fileList);
        return Map.of("ResultCount", resulCnt);
    }


}
