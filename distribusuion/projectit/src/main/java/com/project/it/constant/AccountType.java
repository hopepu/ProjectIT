package com.project.it.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum AccountType implements EnumMapperType{

    REGISTER("등록자"),
    DERIVATION("사용자");

    private String desc;

    //생성자
    private AccountType(String desc){
        this.desc = desc;
    }


    @Override
    @JsonValue
    public String getDesc() {
        return desc;
    }


    @JsonCreator
    public static AccountType from(String desc) {
        for (AccountType status : AccountType.values()) {
            if (status.getDesc().equals(desc)) {
                return status;
            }
        }
        return null;

    }


}
