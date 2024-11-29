package com.project.it.controller.formatter;

import com.project.it.constant.EnumMapperType;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Log4j2
public class StringToStatusConverter implements ConverterFactory<String, Enum<? extends EnumMapperType>> {

    @Override
    public <T extends Enum<? extends EnumMapperType>> Converter<String, T> getConverter(Class<T> targetType) {
        log.info("Controller->converter 실행++++++++++++++");
        if(EnumMapperType.class.isAssignableFrom(targetType)){
            return new StringToEnumConverter<>(targetType);
        }else {
            return null;
        }

    }



    private static final class StringToEnumConverter<T extends Enum<? extends EnumMapperType>> implements Converter<String, T>{
        private final Map<String, T> map;

        //생성자-enum 받아서 map에 넣어줌
        public StringToEnumConverter(Class<T> targetEnum){
            T[] enumConstants = targetEnum.getEnumConstants();
            map = Arrays.stream(enumConstants)
                    .collect(Collectors.toMap(enumConstant->((EnumMapperType)enumConstant).getDesc(), Function.identity()));
        }

        @Override
        public T convert(String source) {
            log.info("Controller->converter-> convert 실행(desc -> enumValue)++++++++++++++");
            //해당값 존재여부 확인
            if(!StringUtils.hasText(source)){
            return null;}

            //해당값 map에서 추출
            T enumValue = map.get(source);
            if(enumValue == null){
                throw  new IllegalArgumentException("IllegalArgumentException");
            }
            return enumValue;
        }


    }
}
