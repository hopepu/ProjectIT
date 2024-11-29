package com.project.it.config;

import com.project.it.controller.formatter.LocalDateFormatter;
import com.project.it.controller.formatter.StringToStatusConverter;
import com.project.it.domain.converter.StatusConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class CustomServletConfig implements WebMvcConfigurer {
    @Override //LocalDateFormatter 설정
    public void addFormatters(FormatterRegistry registry) {
        ConverterFactory converterFactory = new StringToStatusConverter();
        registry.addFormatter(new LocalDateFormatter());
        // controller.formatter.LocalDateFormatter
        registry.addConverterFactory(converterFactory);

    }


}
