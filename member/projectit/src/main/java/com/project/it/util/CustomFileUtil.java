package com.project.it.util;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component //Bean으로 사용
@Log4j2
@RequiredArgsConstructor
public class CustomFileUtil { //파일 입출력 담당(p.185)
    @Value("${fileLocation}")
    private String uploadPath;
    
    @PostConstruct //의존성 주입이 완료된 후에 실행되어야 하는 method, 호출되지 않아도 실행
    public void init(){
        //저장폴더 생성
        File tempFolder = new File(uploadPath);
        if(tempFolder.exists()==false){
            tempFolder.mkdirs();
        }
        uploadPath = tempFolder.getAbsolutePath();
        log.info("--------------업로드 경로 : "+uploadPath);
    }
    //파일저장

    public List<String> saveFiles(List<MultipartFile> files, String uploadPath) throws RuntimeException {
        if (files == null || files.isEmpty()) {
            return List.of(); // 파일이 없으면 빈 리스트 반환
        }

        List<String> uploadNames = new ArrayList<>();
        for (MultipartFile multipartFile : files) {
            String originalFilename = multipartFile.getOriginalFilename();
            if (originalFilename == null) {
                System.out.println("파일 이름이 null입니다. 파일을 건너뜁니다.");
                continue; // 파일 이름이 없으면 건너뛰기
            }

            String savedName = UUID.randomUUID().toString() + "_" + originalFilename; // UUID + 원본 파일 이름
            Path savePath = Paths.get(uploadPath, savedName);

            try {
                // 디렉토리가 없으면 생성
                Files.createDirectories(savePath.getParent());

                // 파일 저장
                Files.copy(multipartFile.getInputStream(), savePath);

                // 이미지 파일이라면 썸네일 생성
                String contentType = multipartFile.getContentType();
                if (contentType != null && contentType.startsWith("image")) {
                    Path thumbnailPath = Paths.get(uploadPath, "s_" + savedName);
                    // 썸네일 크기 (200x200)로 생성
                    Thumbnails.of(savePath.toFile()).size(200, 200).toFile(thumbnailPath.toFile());
                }

                // 업로드된 파일 이름을 리스트에 추가
                uploadNames.add(savedName);

            } catch (IOException e) {
                // 에러 메시지에 파일 이름 포함
                System.out.println("파일 업로드 실패: " + originalFilename + " 오류: " + e.getMessage());
                throw new RuntimeException("파일 업로드 실패: " + originalFilename + " 오류: " + e.getMessage(), e);
            }
        }
        return uploadNames; // 업로드된 파일들의 이름 리스트 반환
    }




    //파일 불러오기
    public ResponseEntity<Resource> getFile(String fileName, String uploadPath){
        Resource resource = new FileSystemResource(uploadPath+File.separator+fileName);
        if(!resource.isReadable()){
            resource =new FileSystemResource(uploadPath+File.separator+"default.jpeg");
        }
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
            //HTTP 헤더 메시지 생성(.getFile() 필수)
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().headers(headers).body(resource);
    }

    //파일 삭제
    public void deleteFiles(List<String> fileNames, String uploadPath){
        if(fileNames == null || fileNames.size()==0){
            return;
        }
        fileNames.forEach(fileName ->{
            //섬네일 있는지 확인하고 삭제
            String thumbnailFileName = "s_"+fileName;
            Path thumbnailPath = Paths.get(uploadPath, thumbnailFileName); //섬네일 경로
            Path filePath = Paths.get(uploadPath, fileName); //원본파일 경로

            try {
                Files.deleteIfExists(filePath);
                Files.deleteIfExists(thumbnailPath);
            }catch (IOException e){
                throw new RuntimeException(e.getMessage());
            }
        });
    }
}
