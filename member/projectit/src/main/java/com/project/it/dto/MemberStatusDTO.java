package com.project.it.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberStatusDTO {

    private String email; // Member 객체 정보
    private Long mno; // Member 객체 정보
    private String password; // Member 객체 정보
    private LocalDateTime start_date; // Member 객체 정보
    private String memberRole; // Member 객체 정보
    private String name;
    private String birth;
    private String tel;
    private String sex;
    private String marital_status;
    private Integer children_count;
    private String qualifications; // 자격증: 여러 개의 자격증을 리스트로 처리
    private String education;
    private String antecedents; // 경력: 여러 개의 경력을 리스트로 처리
    // 팀 정보
    private String team;
    private String teamName;
    // 사진
    //새로운 상품의 등록과 수정 작업시 파일 업로드에 사용(실제 데이터)
    @Builder.Default
    private List<MultipartFile> files = new ArrayList<>();

    // 업로드가 완료된 파일의 이름만 문자열로 보관한 리스트(데이터의 이름과 주소)
    @Builder.Default
    private List<String> uploadFileNames = new ArrayList<>() ;

    //통장 사본
    @Builder.Default
    private List<MultipartFile> files2 = new ArrayList<>();

    // 업로드가 완료된 파일의 이름만 문자열로 보관한 리스트(데이터의 이름과 주소)
    @Builder.Default
    private List<String> uploadFileNames2 = new ArrayList<>() ;
    private String bankCode;
    private String account;

}
