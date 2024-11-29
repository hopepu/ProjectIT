package com.project.it.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcConfig implements WebMvcConfigurer {
    //파일 업로드 경로 설정
    @Value("${uploadPath}")
    String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
       registry.addResourceHandler("/asset_file/**") //브라우저 url 에서 보낼 경로
               .addResourceLocations(uploadPath); //실제 파일 저장될 경로
    }

}
