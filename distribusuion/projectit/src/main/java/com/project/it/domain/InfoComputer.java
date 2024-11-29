package com.project.it.domain;

import com.project.it.constant.ComputerType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "computer_info")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InfoComputer {  //컴퓨터 사양 정보 입력용
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cino;

    private String productName; //제품명
    private String maker; //제조사

    @Enumerated(EnumType.STRING)
    private ComputerType type; //DESKTOP/NOTEBOOK/TABLET

    private String cpu;
    private String gpu;
    private String ram;
    private String osType; //운영체제 종류
    private String capacity; //총 저장공간 용량
    private int price; //단가


}
