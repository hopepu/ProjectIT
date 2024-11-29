package com.project.it.constant.util;

import com.project.it.constant.EnumMapperType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.EnumSet;

//String <->Enum 클래스 변경 공통 작업 관리 위한 클래스
@Log4j2
@NoArgsConstructor(access = AccessLevel.PRIVATE) //기본 생성자를 통한 인스턴스를 생성할 수 없도록 설정
public class StringConverterUtils {
    public static <T extends Enum<T> & EnumMapperType> T ofEnumClass(Class<T> enumClass, String desc){
        //Enum과 desc 받아서 EnumSet(Enum Class) 반환
        if(desc==null){return null;}
        return EnumSet.allOf(enumClass).stream().filter(v->v.getDesc().equals(desc)).findAny().orElseThrow(()->new IllegalArgumentException(String.format("enum=[%s], desc = [%s]가 존재하지 않습니다.", enumClass.getName(), desc)));
    }

    public static <T extends Enum<T> & EnumMapperType> String toEnumName(T enumValue){
        // Enum enum -> enum 열거값(String)-변수명
        if(enumValue==null){
            return null;
        }
        //Enum 전체 리스트 먼저 불러오기
        return enumValue.name();
    }

    public static <T extends Enum<T> & EnumMapperType> T findEnumClass(Class<T> enumClass, String name){
        //Enum과 enum Name(db 저장값) 받아서 EnumSet(Enum Class) 반환
        if(name==null){return null;}
        log.info("StringConverterUtils : findEnumClass+++++++Enum 리스트"+enumClass.getEnumConstants());
        T[] enumList = enumClass.getEnumConstants();
        return EnumSet.allOf(enumClass).stream().filter(v->v.name().equals(name)).findAny().orElseThrow(()->new IllegalArgumentException(String.format("enum=[%s] 가 존재하지 않습니다.", enumClass.getName(), name)));
    }

}
