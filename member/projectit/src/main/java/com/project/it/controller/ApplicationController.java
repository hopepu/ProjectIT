package com.project.it.controller;

import com.project.it.dto.*;
import com.project.it.dto.PageResponseDTO;
import com.project.it.service.ApplicationService;
import com.project.it.util.CustomFileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/it/application")
public class ApplicationController {
    private final ApplicationService appS;
    private final CustomFileUtil fileUtil;

    @GetMapping("/page")
    public PageResponseDTO<ApplicationDTO> pagingAppList(PageRequestDTO pageRequestDTO){
        return appS.getList(pageRequestDTO);
    }

    @PostMapping("/register")
    public Map<String, String> register(ApplicationDTO applicationDTO) {
        log.info("등록컨트롤러 시작");
        log.info("register : " + applicationDTO);

        List<String> uploadFileNames = fileUtil.saveFiles(applicationDTO.getFiles(), uploadPath);  // 파일 저장 처리
        log.info(uploadFileNames);

        applicationDTO.setUploadFileNames(uploadFileNames);

        Long msg = appS.register(applicationDTO);  // 등록 처리

        log.info(msg);
        return Map.of("RESULT", msg.toString());
    }

    @GetMapping("/getOne/{mno}")
    public ApplicationDTO oneRead(@PathVariable(name = "mno") String mno){
        return appS.getOne(mno);
    }

    @Value("${applicationFileLocation}")  // application.properties에서 경로 설정
    private String uploadPath;


    @GetMapping("/getFile/{fileName}")
    public ResponseEntity<Resource> getFile(@PathVariable String fileName) {

        String url = uploadPath + "/" + fileName;
        log.info("URL 주소 : " + url);

        Resource resource = new FileSystemResource(uploadPath+ File.separator+fileName);

        // 파일이 존재하지않거나, 읽을수없는경우 기본이미지를 반환함
        if(!resource.isReadable()){
            resource =new FileSystemResource(uploadPath+File.separator+"default.jpeg");
        }
        HttpHeaders headers = new HttpHeaders();

        log.info("resource data : " + resource );

        try {
            headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
            //HTTP 헤더 메시지 생성(.getFile() 필수)
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().headers(headers).body(resource);
    }

    @PostMapping("/modify")
    public Map<String, String> modify(ApplicationDTO applicationDTO) {
        log.info("수정컨트롤러 시작");
        log.info("modify : " + applicationDTO);

        Long msg = appS.modify(applicationDTO);  // 수정 처리

        log.info(msg);
        return Map.of("RESULT", msg.toString());

    }


}
