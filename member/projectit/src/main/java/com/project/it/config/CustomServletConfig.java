package com.project.it.config;

import com.project.it.controller.formatter.LocalDateFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class CustomServletConfig implements WebMvcConfigurer {

    @Override //LocalDateFormatter 설정
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new LocalDateFormatter());
        // controller.formatter.LocalDateFormatter
    }

    @Override // Ajax을 이용해서 호출시 교차 출처 리소스 공유(CORS) 제한이 됨 -> 리엑트에서 스프링 부트로 동작하는 서버를 호출해야됨
    public void addCorsMappings(CorsRegistry registry) { // import org.springframework.web.servlet.config.annotation.CorsRegistry

        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("HEAD", "GET", "POST", "PUT", "DELETE", "OPTIONS")
                .maxAge(300)
                .allowedHeaders("Authorization", "Cache-Control", "Content-Type");
    }


}
