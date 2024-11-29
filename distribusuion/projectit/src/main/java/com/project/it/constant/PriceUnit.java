package com.project.it.constant;

import com.project.it.domain.converter.StatusConverter;
import jakarta.persistence.Converter;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum PriceUnit implements EnumMapperType{
    PERSON("인"), //인당
    MONTHLY("월"), //월단위
    YEAR("년");

    private String desc; //문자열 저장을 위한 필드
    //생성자
   private PriceUnit(String desc) {
       this.desc = desc;
     //  getDesc();
    }


    @Override
    public String getDesc() { //String 으로 배출
        return desc;
    }

    public PriceUnit parse(String desc) {
        return Arrays.stream(values()).filter(s->s.desc==desc)
                .findFirst().orElse(PriceUnit.YEAR);
    }


}
