package com.project.it.service;

import com.project.it.domain.FileUpload;

import com.project.it.dto.FileUploadDTO;
import com.project.it.dto.PageRequestDTO;
import com.project.it.dto.PageResponseDTO;
import com.project.it.repository.FileRepository;
import com.project.it.util.CustomFileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class FileListServiceImpl implements FileListService {
    private final FileRepository fileRepository;
    private final CustomFileUtil fileUtil;

    @Override
    public PageResponseDTO<FileUploadDTO> getFileList(PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() - 1, pageRequestDTO.getSize(), Sort.by("fno").descending());

        Page<FileUpload> result = fileRepository.searchAllWithPaging(pageable);
        List<FileUploadDTO> dtoList = result.get().map(fileEn -> {
            FileUploadDTO dto = entityToDto(fileEn);
            return dto;
        }).collect(Collectors.toList());
        long totalCount = result.getTotalElements();

        return PageResponseDTO.<FileUploadDTO>withAll().dtoList(dtoList).pageRequestDTO(pageRequestDTO).totalCount(totalCount).build();
    }

    @Override
    public PageResponseDTO<FileUploadDTO> searchFileList(PageRequestDTO pageRequestDTO, boolean state) {
        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() - 1, pageRequestDTO.getSize(), Sort.by("fno").descending());
        Page<FileUpload> result;
        if (state) {
            result = fileRepository.searchFilesWithPaging(pageable);
        } else {
            result = fileRepository.searchDeletedFileWithPaging(pageable);
        }

        List<FileUploadDTO> dtoList = result.get().map(fileEn -> {
            FileUploadDTO dto = entityToDto(fileEn);
            return dto;
        }).collect(Collectors.toList());
        long totalCount = result.getTotalElements();

        return PageResponseDTO.<FileUploadDTO>withAll().dtoList(dtoList).pageRequestDTO(pageRequestDTO).totalCount(totalCount).build();
    }


    @Override
    public int restore(List<Long> fileNums) { //삭제처리된 것 복구
        log.info("=======fileList 복구요청 리스트 개수 : "+fileNums.size());
        int resulCnt = 0; //삭제결과
        for(Long fno:fileNums){
            int cnt = fileRepository.updateFileState(fno, true);
            resulCnt +=cnt;
        }
        log.info("=======fileList 복구완료 개수 : "+resulCnt);
        return resulCnt;
    }

    @Override
    public int deleteForever(List<Long> fileNums) {
        //실제 파일 삭제
        List<FileUpload> result = fileRepository.findAllById(fileNums);
        List<FileUploadDTO> removeFiles = result.stream().map(entity->{
            FileUploadDTO fileDto = new FileUploadDTO();
            fileDto.setFno(entity.getFno());
            fileDto.setCategory(entity.getCategory());
            fileDto.setAssetNum(entity.getAssetNum());
            fileDto.setOriginFileName(entity.getOriginFileName());
            fileDto.setSaveFileName(entity.getSaveFileName());
            fileDto.setFolderPath(entity.getFolderPath());
            fileDto.setSize(entity.getSize());
            fileDto.setDeleteOrNot(entity.isDeleteOrNot());
            return fileDto;
        }).collect(Collectors.toList());

        fileUtil.deleteFiles(removeFiles);

        //db 삭제
        int resulCnt = 0; //삭제결과
        for(Long fno:fileNums){
            int cnt = fileRepository.deleteByFileList(fno);
            resulCnt +=cnt;
        }
        log.info("=======fileList 삭제완료 개수 : "+resulCnt);
        return resulCnt;
    }

    private String changeCategory(String category) {
        String str = switch (category) {
            case "business" -> str = "고객사";
            case "license" -> str = "라이선스";
            case "company" -> str = "회사관련";
            case "storage" -> str = "별도저장매체";
            default -> "";
        };
        return str;
    }

    private FileUploadDTO entityToDto(FileUpload entity) {
        FileUploadDTO dto = new FileUploadDTO();
        dto.setFno(entity.getFno());
        dto.setCategory(changeCategory(entity.getCategory()));
        dto.setAssetNum(entity.getAssetNum());
        dto.setOriginFileName(entity.getOriginFileName());
        dto.setSaveFileName(entity.getSaveFileName());
        dto.setFolderPath(entity.getFolderPath());
        dto.setSize(entity.getSize());
        dto.setDeleteOrNot(entity.isDeleteOrNot());
        return dto;
    }
}
