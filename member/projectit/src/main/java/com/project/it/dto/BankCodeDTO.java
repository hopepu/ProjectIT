package com.project.it.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class BankCodeDTO {
    private String code;
    private String bankName;

    public BankCodeDTO(String code, String bankName) {
        this.code = code;
        this.bankName = bankName;
    }

    public String getCode() {
        return code;
    }

    public String getBankName() {
        return bankName;
    }
}
