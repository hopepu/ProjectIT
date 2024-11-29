package com.project.it.dto;

import com.project.it.constant.AccountType;
import lombok.*;

@Data
@Getter
@Setter
public class AccountObjectOPDTO {
    private String ac_id;
    private String ac_pw;
    private String accountType;

    public AccountObjectOPDTO(String ac_id, String ac_pw, AccountType accountType){
        this.ac_id = ac_id;
        this.ac_pw = ac_pw;
        this.accountType = accountType.getDesc();
    }
}
