package com.example.bootagit_project01.user.service;

import com.example.bootagit_project01.user.dto.UserDto;
import com.example.bootagit_project01.user.entity.User;
import com.example.bootagit_project01.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/*
* @Service 어노테이션 :
*
* */
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;


    public User registerUser(UserDto userDTO) {
        User user = new User();
        user.setPassword(userDTO.getPassword());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        return userRepository.save(user);
    }


}
