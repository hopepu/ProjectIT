package com.project.it.domain;

import com.project.it.constant.ContractStatus;
import com.project.it.constant.RightType;
import com.project.it.constant.UsePurpose;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Entity
@Table(name = "license_asset")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"license"})
@DynamicInsert
@DynamicUpdate
public class AssetLicense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long ano; //관리번호

    @Enumerated(EnumType.STRING)
    @Column(name = "rightType", nullable = false)
    private RightType rightType; //권리유형 : 자사특허, (타사)사용권

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContractStatus contractStatus; //계약구분(신규, 재계약, 갱신..)
    private LocalDate contractDate; //취득일(계약일)
    private LocalDate expireDate; //만료일
    private int contractCount; //상품구입 개수
    private int totalPrice; //구입총액(count*contractCount)

   @Enumerated(EnumType.STRING)
   @Column(nullable = false)
    private UsePurpose usePurpose; //사용목적

    @Lob
    private String comment; //기타 설명

    @ColumnDefault("false")
    private boolean expireYN; //만료여부
    @ColumnDefault("false")
    private boolean deleteOrNot; //삭제처리 여부

    private int totalUseCount; //총 사용가능 개수(maxcount*contractCount)
    private int currentUseCount ; //현재 사용중 개수

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "license_id")
    private InfoLicense license; //관련 라이선스 상품
    
    private int fileCount; //첨부파일 개수

    //Method
    public void changeComment(String comment){
        this.comment = comment;
    }

    public void changeDeleteState(boolean deleteOrNot){
        this.deleteOrNot = deleteOrNot;
    }

    @PrePersist //insert 전 수행
    public void initTotalUseCount(){
        int maxUser = this.license.getMaxUserCount();
        int contractStatus = this.contractCount;
        this.totalUseCount = maxUser*contractStatus;
    }


    public void plusCurrentCount(int count){
        this.currentUseCount += count;
    }
    public void minusCurrentCount(int count){
        this.currentUseCount -= count;
    }


}

//    create table license_asset (
//        ano bigint not null auto_increment,
//        reg_date date,
//        modified_by varchar(255),
//    update_date date,
//    created_by varchar(255),
//    comment tinytext,
//    contract_count integer not null,
//        contract_date date,
//        contract_status enum ('CANCEL','EXPIRE','EXTENSION','NEW','RENEWAL'),
//        delete_or_not bit not null,
//        expire_date date,
//        expireyn bit not null,
//        type varchar(255),
//        license_id bigint,
//        primary key (ano)
//        ) engine=InnoDB
//alter table if exists license_asset
//        drop index if exists UK_e7bky6m5cqc2nyh2l8pccviw7
//        Hibernate:
//        alter table if exists license_asset
//        add constraint UK_e7bky6m5cqc2nyh2l8pccviw7 unique (license_id)

//    alter table if exists license_asset
//        add constraint FKo5jd6vg7vc33g3rlddcowkbhb
//        foreign key (license_id)
//        references license_info (lno)