package com.project.it.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //스프링에서 설정파일 역할
public class RootConfig {
    //ModelMapper 설정
    @Bean
    public ModelMapper getMapper(){ //**11.05 Custom
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper;
    }
}

// modelMapper.getConfiguration()
//         .setFieldMatchingEnabled(true)
//         .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
//         .setMatchingStrategy(MatchingStrategies.LOOSE);
