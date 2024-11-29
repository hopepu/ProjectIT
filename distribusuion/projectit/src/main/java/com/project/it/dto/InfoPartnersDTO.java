package com.project.it.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InfoPartnersDTO { //고객사 정보 관리용DTO
    private Long cno;
    private String comName;
    private String coNum;
    private String phone;
    private String site;
    private String address;
    private String bizType;
}
