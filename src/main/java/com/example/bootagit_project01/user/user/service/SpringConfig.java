package com.example.bootagit_project01.user.user.service;

import com.example.bootagit_project01.user.user.repository.JpaUserRepository;
import com.example.bootagit_project01.user.user.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Autowired private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }


   @Bean
    public UserService userService(){
        return new UserService(userRepository(), new ModelMapper()); }

    @Bean
    public UserRepository userRepository() {
        return new JpaUserRepository(em);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
