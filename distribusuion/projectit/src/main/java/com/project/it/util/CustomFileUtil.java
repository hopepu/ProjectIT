package com.project.it.util;

import com.project.it.dto.FileUploadDTO;
import com.project.it.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Log4j2
@RequiredArgsConstructor
public class CustomFileUtil { //공통 파일 처리
    @Value("${fileLocation}")
    private String uploadPath;

    @Value("${businessFileLocation}")
    private String path_business; //고객사 관련 파일 폴더
    @Value("${licenseFileLocation}")
    private String path_license; //sw 라이선스 관련 파일 폴더
    @Value("${companyFileLocation}")
    private String path_company; //자사 관련 파일 폴더

    private final FileRepository fileRepository;

    //파일저장경로 판단(category/assetnum) + 생성
    private String makeSavePath(String category, Long assetNum) {
        //--switch()
        String uploadPath = switch (category) {
            case "business" -> path_business;
            case "license" -> path_license;
            case "company" -> path_company;
            default -> "";
        };
        uploadPath = uploadPath.replace("/", File.separator);
        log.info("uploadPath:" + uploadPath);
        String myPath = uploadPath + File.separator + assetNum;
        log.info("myPath:" + myPath);

        File tempFolder = new File(myPath);
        if (!tempFolder.exists()) {
            tempFolder.mkdirs();
        }
        myPath = tempFolder.getAbsolutePath();
        return myPath;
    }

    //파일저장명 생성
    private String makeSaveFileName(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf("."));//확장자
        UUID uuid = UUID.randomUUID();
        return uuid + extension;
    }

    //파일저장
    @Transactional
    public List<FileUploadDTO> saveFiles(List<MultipartFile> files, String category, Long assetNum) throws RuntimeException {
        if (files == null || files.size() == 0) {
            return List.of();
        }
        //폴더 생성
        String myPath = makeSavePath(category, assetNum);
        log.info("저장경로 : " + myPath);
        String saveFolderPath = category+"/"+assetNum;
        //dto
        List<FileUploadDTO> uploadDTOS = new ArrayList<>();
        for (MultipartFile multipartFile : files) {
            String saveName = makeSaveFileName(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            Path savePath = Paths.get(myPath, saveName);
            FileUploadDTO fileUploadDTO = new FileUploadDTO();
            fileUploadDTO.setCategory(category);
            fileUploadDTO.setAssetNum(assetNum);
            fileUploadDTO.setOriginFileName(multipartFile.getOriginalFilename());
            fileUploadDTO.setSaveFileName(saveName);
            fileUploadDTO.setFolderPath(saveFolderPath);
            fileUploadDTO.setSize(multipartFile.getSize());

            uploadDTOS.add(fileUploadDTO); //db 저장할 dto list 준비
            try {
                Files.copy(multipartFile.getInputStream(), savePath); //파일저장
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return uploadDTOS;
    }


    //파일불러오기
    public ResponseEntity<Resource> getFile(FileUploadDTO fileUploadDTO) {
        String savedPath = fileUploadDTO.getFolderPath();//category+assetNum
        String fileName = fileUploadDTO.getSaveFileName();
        String fullPath = File.separator +savedPath + File.separator + fileName;
        Path filePath = Paths.get(uploadPath+File.separator+fullPath);
        log.info("FileUtil+++++++filePATH : "+ filePath);
        Resource resource = new FileSystemResource(filePath);
        if (!resource.isReadable()) {
            resource = new FileSystemResource(uploadPath + File.separator + "default.jpeg");
        }
        HttpHeaders headers = new HttpHeaders();

        try {
            headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().headers(headers).body(resource);
    }

    //파일삭제
    public void deleteFiles(List<FileUploadDTO> files) {
        if (files == null || files.size() == 0) {
            return;
        }
        files.forEach(fileUploadDTO -> {
            //원본파일 경로 찾기
            Path filePath = Paths.get(fileUploadDTO.getFolderPath(), fileUploadDTO.getSaveFileName());

            try {
                Files.deleteIfExists(filePath);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        });

    }

    //파일변경-동일파일 검색, 기존파일 삭제 후 신규만 저장, dto list만 리턴
    public List<FileUploadDTO> updateFiles(List<MultipartFile> newFileList, String category, Long assetNum) {
        //기존 파일 찾아오기
        List<FileUploadDTO> oldFiles = fileRepository.findAssetFileList(category, assetNum);
        if (oldFiles == null || oldFiles.size() == 0) {
            return List.of();
        }

        //기존파일에 없는 신규파일(추가포함)
        List<MultipartFile> newSaveFiles = newFileList.stream().filter(newFile
                ->oldFiles.stream().noneMatch(oldFile->{
                    return newFile.getOriginalFilename().equals(oldFile.getOriginFileName()) && newFile.getSize()==oldFile.getSize();
        })).collect(Collectors.toList());
        newSaveFiles.forEach(saveFile->log.info("신규파일 name"+saveFile.getOriginalFilename()));

        //삭제할 파일용 리스트
        List<FileUploadDTO> removeFiles = oldFiles.stream().filter(oldFile
                -> newFileList.stream().noneMatch(newFile->{
                    return oldFile.getOriginFileName().equals(newFile.getOriginalFilename()) && oldFile.getSize() == newFile.getSize();
        })).collect(Collectors.toList());
        removeFiles.forEach(removeFile->log.info("지울파일 name"+removeFile.getOriginFileName()));


        if(removeFiles != null && removeFiles.size()>0){
            deleteFiles(removeFiles);
            removeFiles.forEach(removeFile->{
                fileRepository.updateDelState(removeFile.getCategory(), removeFile.getAssetNum(), true); //삭제표시
            });
        }
        //신규파일 data 저장
        List<FileUploadDTO> newSavedFiles= saveFiles(newSaveFiles, category, assetNum);
        return newSavedFiles;
    }


}
