package com.project.it.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "license_manage", indexes = {@Index(name = "idx_mng_license", columnList = "asset_id")})
@Getter
@ToString(exclude = "assetLicense")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ManageLicense extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sno;

    private String docuNum; //문서번호
    private int useCount; //현재 사용중 개수
    private int requestCount; //요청개수(00명 필요)
    private String reqPurpose; //요청사유
    
    private boolean deleteOrNot; //(문서)삭제처리 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "asset_id")
    private AssetLicense assetLicense; //라이센스 정보(계약한 건)

    //변경용 메서드
    public void changeDeleteOrNot(boolean deleteOrNot){
        this.deleteOrNot = deleteOrNot;
    }

}

//    create table software_manage (
//        sno bigint not null auto_increment,
//        reg_date date,
//        modified_by varchar(255),
//    update_date date,
//    created_by varchar(255),
//    delete_or_not bit not null,
//        sw_document varchar(255),
//        req_purpose varchar(255),
//        request_count integer not null,
//        use_count integer not null,
//        asset_license_id bigint,
//        primary key (sno)
//        ) engine=InnoDB
//create index idx_sw_document
//        on software_manage (sw_document)
//alter table if exists software_manage
//        add constraint FKben0kxwdlemyajil7o6k3hxar
//        foreign key (asset_license_id)
//        references license_asset (ano)