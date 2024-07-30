package com.example.bootagit_project01.config;

import com.example.bootagit_project01.jwt.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtAuthConfig {
    @Bean //(name = "")
    public JwtUtil jwtUtil() {
        return new JwtUtil("sklskljsklsjalkjklsjSKLSAKLJsklsklsjlksjsakljslkajsalksaksa",
                10 * 60 * 1000, 24 * 60 * 60 * 1000);
    }

}
