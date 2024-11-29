package com.project.it.dto;

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
public class AssetComputerDTO { //컴퓨터 구입요청/계약용
    private Long cno;
    private Long cino; //--info id
    private String productName; //제품명--info
    private int price; //단가 --info

    private String purpose; //구입목적
    private int contractCount; //구입개수
    private int totalPrice; //총 금액
    private LocalDate contractDate; //구입일
    private boolean deleteOrNot; //삭제처리용
    @Builder.Default
    private List<MultipartFile> files = new ArrayList<>(); //첨부파일

}
