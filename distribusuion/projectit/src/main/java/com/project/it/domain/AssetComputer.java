package com.project.it.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Entity
@Table(name = "computer_asset")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "infoComputer")
@DynamicInsert
@DynamicUpdate
public class AssetComputer { //컴퓨터 구입관리
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cno;


    private String purpose; //구입목적
    private int contractCount; //구입개수
    private int totalPrice; //총 금액
    private LocalDate contractDate; //구입일

    @ColumnDefault("false")
    private boolean deleteOrNot; //삭제처리용

    @ManyToOne
    @JoinColumn(name = "infoComputer_id")
    private InfoComputer infoComputer;

    //--------method
    public void changePurpose(String purpose){
        this.purpose = purpose;
    }

    public void changeCountAndTotal(int count){
        int price = this.infoComputer.getPrice();
        this.contractCount = count;
        this.totalPrice = count*price;
    }

    public void changeDelOrNot(boolean deleteOrNot){
        this.deleteOrNot=deleteOrNot;
    }
    public void changeContractDate(LocalDate contractDate){
        this.contractDate = contractDate;
    }

}
