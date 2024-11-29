package com.project.it.constant;

import com.project.it.domain.converter.StatusConverter;
import jakarta.persistence.Converter;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum RightType implements EnumMapperType{ //권리 유형
    OPENSOURCE("오픈소스"), //오픈소스
    LICENSE("[구입]사용권"), //사용권
    COPYRIGHT("[보유]저작권"), //저작권(자사소유)
    PATENT("[보유]특허"), //특허
    INSTALLATION("[설치]영구사용");

    private String desc;
    private RightType(String desc) {
        this.desc = desc;
    }


    @Override
    public String getDesc() { //String 으로 배출
        return desc;
    }

    public RightType parse(String desc) {
        return Arrays.stream(values()).filter(s->s.desc==desc)
                .findFirst().orElse(RightType.LICENSE);
    }


}
