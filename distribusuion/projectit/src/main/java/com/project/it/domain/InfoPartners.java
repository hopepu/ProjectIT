package com.project.it.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "partners_info", indexes = {@Index(name = "idx_company_name", columnList = "comName")})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class InfoPartners { //협력사 등 회사 정보 저장용
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cno;
    
    private String comName;
    private String coNum;
    private String phone;
    private String site;
    private String address;
    private String bizType;

    private String ceoName;


    //변경용 method
    public void changeComName(String comName){
        this.comName = comName;
    }
    public void changeCoNum(String coNum){
        this.coNum = coNum;
    }
    public void changePhone(String phone){
        this.phone = phone;
    }
    public void changeSite(String site){
        this.site = site;
    }
    public void changeAddress(String address){
        this.address = address;
    }
    public void changeBizType(String bizType){
        this.bizType = bizType;
    }
}

//    cno bigint not null auto_increment,
//        address varchar(255),
//        biz_type varchar(255),
//        co_num varchar(255),
//        com_name varchar(255),
//        phone varchar(255),
//        site varchar(255),
//        primary key (cno)