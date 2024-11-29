package com.project.it.controller;

import com.project.it.domain.BankCode;
import com.project.it.dto.BankCodeDTO;
import com.project.it.dto.MemberStatusDTO;
import com.project.it.service.MemberStatusService;
import com.project.it.util.CustomFileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/it/members")
public class MemberController {

    private final MemberStatusService memberStatusService;
    private final CustomFileUtil fileUtil;

    @GetMapping("/bankcode")
    public ResponseEntity<List<BankCodeDTO>> getAllBankCodes() {
        List<BankCodeDTO> bankCodes = Arrays.stream(BankCode.values())
                .map(bankCode -> new BankCodeDTO(bankCode.getCode(), bankCode.getBankName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(bankCodes);
    }


    @PostMapping("/register")
    public Map<String, String> register(MemberStatusDTO R) {
        log.info(R);

        String msg = memberStatusService.register(R);

        return Map.of("RESULT", msg);
    }

    @PostMapping("/account")
    public Map<String, String> account(MemberStatusDTO R) {
        log.info("입력된 값 : " + R);

        String msg = memberStatusService.registerOne(R);

        return Map.of("RESULT", msg);

    }

    @GetMapping("/one/{data}")
    public int getOne(@PathVariable String data) {
        MemberStatusDTO D = MemberStatusDTO.builder()
                .email(data)
                .tel(data)
                .build();

        int result = memberStatusService.read(D);
        return result;
    }



    @GetMapping("/{mno}")
    public MemberStatusDTO statusRead(@PathVariable(name = "mno") Long mno){
        log.info(mno);

        MemberStatusDTO memberStatusDTO = memberStatusService.readOne(mno);
        log.info(memberStatusDTO);

        return memberStatusDTO;
    }

    @Value("${statusFileLocation}")  // application.properties에서 경로 설정
    private String uploadPath;

    @Value("${bankFileLocation}")
    private String uploadPath2;

    @PostMapping("/modify")
    public Map<String, String> modify(
            @ModelAttribute MemberStatusDTO R, // 폼 데이터 바인딩
            @RequestParam("files") List<MultipartFile> files, // 파일을 명시적으로 바인딩
            @RequestParam("files2") List<MultipartFile> files2
    )  {
        log.info("modify controller 시작");
        log.info("modify 내용 " + R);



        // 파일 저장 처리
        List<String> uploadFileNames = fileUtil.saveFiles(files, uploadPath);
        log.info(uploadFileNames);

        List<String> uploadFileNames2 = fileUtil.saveFiles(files2, uploadPath2);
        log.info(uploadFileNames2);

        // 파일 저장 후 파일 이름을 DTO에 세팅
        R.setUploadFileNames(uploadFileNames);

        R.setUploadFileNames2(uploadFileNames2);

        // 서비스로 데이터 처리
        String msg = memberStatusService.modifyOne(R);

        // 메시지 로깅
        log.info(msg);

        // 결과 반환
        return Map.of("RESULT", msg);
    }

    @GetMapping("/getFile2/{fileName2}")
    public ResponseEntity<Resource> getFile2(@PathVariable String fileName2) {
        log.info("File path: " + uploadPath2);

        // 경로 정리: 안전한 경로 결합
        Path filePath = Paths.get(uploadPath2, fileName2).normalize();
        log.info("Requested file path: " + filePath);

        // 파일이 존재하고 읽을 수 있는지 확인
        Resource resource = new FileSystemResource(filePath);
        if (!resource.exists() || !resource.isReadable()) {
            // 파일이 없거나 읽을 수 없으면 기본 이미지로 대체
            log.warn("Requested file not found or unreadable, returning default image.");
            resource = new FileSystemResource(Paths.get(uploadPath2, "default.jpeg"));
        }

        HttpHeaders headers = new HttpHeaders();
        try {
            // 파일 타입을 HTTP 헤더에 설정
            String contentType = Files.probeContentType(resource.getFile().toPath());
            headers.add("Content-Type", contentType != null ? contentType : "application/octet-stream");
        } catch (IOException e) {
            log.error("Failed to determine file content type: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok().headers(headers).body(resource);
    }

    @GetMapping("/getFile/{fileName}")
    public ResponseEntity<Resource> getFile(@PathVariable String fileName) {
        log.info("File path: " + uploadPath);

        // 경로 정리: 안전한 경로 결합
        Path filePath = Paths.get(uploadPath, fileName).normalize();
        log.info("Requested file path: " + filePath);

        // 파일이 존재하고 읽을 수 있는지 확인
        Resource resource = new FileSystemResource(filePath);
        if (!resource.exists() || !resource.isReadable()) {
            // 파일이 없거나 읽을 수 없으면 기본 이미지로 대체
            log.warn("Requested file not found or unreadable, returning default image.");
            resource = new FileSystemResource(Paths.get(uploadPath, "default.jpeg"));
        }

        HttpHeaders headers = new HttpHeaders();
        try {
            // 파일 타입을 HTTP 헤더에 설정
            String contentType = Files.probeContentType(resource.getFile().toPath());
            headers.add("Content-Type", contentType != null ? contentType : "application/octet-stream");
        } catch (IOException e) {
            log.error("Failed to determine file content type: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok().headers(headers).body(resource);
    }
}
