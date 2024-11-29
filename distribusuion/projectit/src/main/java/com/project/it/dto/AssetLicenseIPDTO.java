package com.project.it.dto;

import com.project.it.constant.ContractStatus;
import com.project.it.constant.RightType;
import com.project.it.constant.UsePurpose;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssetLicenseIPDTO {
    private Long ano;
    private RightType rightType; //권리유형 : 자사특허, (타사)사용권
    private ContractStatus contractStatus; //계약구분(신규, 재계약, 갱신..)
    private LocalDate contractDate; //취득일(계약일)
    private LocalDate expireDate; //만료일
    private int contractCount; //상품구입 개수
    private int totalPrice; //구입총액(count*contractCount)
    private String comment; //기타 설명
    private UsePurpose usePurpose; //사용목적
    private boolean expireYN; //만료여부
    private boolean deleteOrNot; //삭제처리 여부

    private int totalUseCount; //총 사용가능 개수(maxcount*contractCount)


    private Long licenseId; //관련 라이선스 id

    @Builder.Default
    private List<MultipartFile> files = new ArrayList<>(); //첨부파일(들어올 때)


    private int fileCount; //첨부파일 개수



}
