package com.project.it.domain.converter;

import com.project.it.constant.EnumMapperType;
import com.project.it.constant.util.StringConverterUtils;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.extern.log4j.Log4j2;

//Enum -> DB 열거값 자동 converter
//StringConverterUtils 클래스 이용
@Log4j2
@Converter(autoApply = true) //jpa에서 자동적용
public abstract class StatusConverter<E extends Enum<E> & EnumMapperType> implements AttributeConverter<E, String>{
    //<X, Y> X : 데이터베이스에서 읽어와서 사용할 타입, 즉 Enum 타입
    //<X, Y> Y :  데이터베이스에 저장할 문자열 타입

    private Class<E> targetEnumClass;
    private String enumName;
    private boolean nullable;

    //생성자--Allargus
    public StatusConverter(Class<E> targetEnumClass, String enumName, boolean nullable){
        this.targetEnumClass = targetEnumClass;
        this.enumName = enumName; //EnumClass 이름(db 열이름)
        this.nullable = nullable;
    }

    @Override
    public String convertToDatabaseColumn(E attribute) {
        // X -> Y 엔터티 속성에 저장된 값을 데이터베이스에 저장될 데이터 표현으로 변환
        //attribute : 변환할 엔터티 속성 값
        //String : 데이터베이스 열 이름
        log.info("AttributeConverter 실행(db Column name 추출)++++++++++++++");
        if(!nullable && attribute == null){
            throw new IllegalArgumentException(String.format("%s(은)는 NULL로 저장할 수 없습니다.", enumName));
        }
        return StringConverterUtils.toEnumName(attribute);
    }

    @Override
    public E convertToEntityAttribute(String dbData) {
        //Y -> X
        //데이터베이스 컬럼에 저장된 데이터를 엔터티 속성에 저장될 값으로 변환
        log.info("AttributeConverter 실행(db Column 속성 추출)++++++++++++++");
        if(!nullable && dbData == null){
            throw new IllegalArgumentException(String.format("%s(이)가 DB에 NULL 혹은 Empty로 저장되어 있습니다.", enumName));
        }
        return StringConverterUtils.findEnumClass(targetEnumClass, dbData);
    }
}