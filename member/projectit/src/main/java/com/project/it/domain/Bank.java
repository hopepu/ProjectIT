package com.project.it.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bank")
public class Bank {
    @Id
    @GeneratedValue
    private Long bno;

    private String account;

    @ManyToOne
    @JoinColumn(name = "member_mno")
    private Member member;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<BankFile> BankFileList = new ArrayList<>();
    // 사진 파일 관련

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<BankCode> bankCodeList = new ArrayList<>();

    public void addBankCode(BankCode bankCode) {
        if(bankCodeList.size()>0){
            bankCodeList.remove(0);
        }
        bankCodeList.add(bankCode);
    }

    public void addAppFile(BankFile file) {

        file.setOrd(this.BankFileList.size());
        BankFileList.add(file);
    }

    public void addAppString(String fileName){

        BankFile bankFile = BankFile.builder()
                .fileName(fileName)
                .build();
        addAppFile(bankFile);

    }

}
