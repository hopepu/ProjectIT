package com.project.it.dto;

import com.project.it.constant.ContractStatus;
import com.project.it.constant.PriceUnit;
import com.project.it.constant.RightType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssetLicenseOneDTO { //하나 검색용(info+asset)

    private Long ano;
    private String rightName; //이름(특허/계약명) --info
    private String purpose; //용도 : 디자인, 개발.. --info
    private String rightType; //권리유형 : 자사특허, (타사)사용권 enum RightType
    private String usePurpose; //사용목적
    private String contractStatus; //계약구분(신규, 재계약, 갱신..) enum
    private LocalDate contractDate; //취득일(계약일)
    private LocalDate expireDate; //만료일
    private int contractCount; //상품구입 개수
    private String comment; //기타 설명
    private boolean expireYN; //만료여부
   // private boolean deleteOrNot; //삭제처리 여부

    private int totalPrice; //금액(계약총액)  --asset
    private int price; //금액(계약총액) --info
    private String priceUnit; //계약단위(월단위, 년단위, 인원수 단위...) --info enum

    private int maxUserCount; //최대 사용 가능 인원  --info
    private int totalUseCount; //총 사용가능 개수(maxcount*contractCount)
    private int currentUseCount ; //현재 사용중 개수

    private Long licenseId; //관련 라이선스 id --info

    @Builder.Default
    private List<FileUploadDTO> savedFiles = new ArrayList<>(); //--file

    private int fileCount; //첨부파일 개수 //--file

    private String contact; //이용경로 --info

}
