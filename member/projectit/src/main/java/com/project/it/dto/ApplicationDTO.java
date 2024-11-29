package com.project.it.dto;

import com.project.it.domain.JoinStatus;
import com.project.it.domain.OrganizationTeam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDTO {

    private Long no;

    private String name;

    private String phoneNum;
    
    private String mail;

    private LocalDateTime start_date;

    @Builder.Default
    private List<String> joinStatus = new ArrayList<>();

    @Builder.Default
    private List<String> organizationTeam = new ArrayList<>();

    //새로운 상품의 등록과 수정 작업시 파일 업로드에 사용(실제 데이터)
    @Builder.Default
    private List<MultipartFile> files = new ArrayList<>();

    // 업로드가 완료된 파일의 이름만 문자열로 보관한 리스트(데이터의 이름과 주소)
    @Builder.Default
    private List<String> uploadFileNames = new ArrayList<>() ;


}
