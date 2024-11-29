package com.project.it.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account_list", indexes = {@Index(name = "idx_account_site", columnList = "siteName")})
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class ListAccount { //사용경로, 계정만 관리
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long siNum; //관리번호

    private String siteName; //사이트 이름

    @Lob
    @Column(nullable = false)
    private String siteUrl; // 접속 경로
    @ColumnDefault("true")
    private boolean useState; //사용여부
    @ColumnDefault("false")
    private boolean userAuthentication; //개인 실명인증 여부

    @ElementCollection
    @Builder.Default
    List<AccountObject> accountObjectList = new ArrayList<>(); //사용자 계정리스트


    @ManyToOne
    @JoinColumn(name = "asset_id")
    private AssetLicense assetLicense; //관련 라이선스(최대 사용자, 번호)

    //Method
    public void changeSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public void addAccountList(AccountObject accountObject){
        accountObjectList.add(accountObject);
    }

    public void clearList(){
        this.accountObjectList.clear();
    }

    public void changeUseState(boolean flag){
        this.useState = flag;
    }

    public void changeUserAuthentication(boolean flag){
        this.userAuthentication=flag;
    }
}
