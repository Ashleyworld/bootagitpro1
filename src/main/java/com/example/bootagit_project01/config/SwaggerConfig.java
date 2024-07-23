// SwaggerConfig.java
package com.example.bootagit_project01.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// 기존의 Swagger 관련 import문들

@Configuration
public class SwaggerConfig {

    // 기존의 Swagger 관련 설정 메서드들

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}