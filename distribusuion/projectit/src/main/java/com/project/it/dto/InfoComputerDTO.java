package com.project.it.dto;

import com.project.it.constant.ComputerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InfoComputerDTO { //컴퓨터 사양 정보 입력용
    private Long cino;
    private String productName; //제품명
    private String maker; //제조사
    private ComputerType type; //DESKTOP/NOTEBOOK/TABLET
    private String cpu;
    private String gpu;
    private String ram;
    private String osType;
    private String capacity; //총 저장공간 용량
    private int price; //단가
}
