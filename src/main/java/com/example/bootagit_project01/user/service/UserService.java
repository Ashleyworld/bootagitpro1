package com.example.bootagit_project01.user.service;

import com.example.bootagit_project01.user.dto.UserDto;
import com.example.bootagit_project01.user.entity.User;
import com.example.bootagit_project01.user.repository.MemoryUserRepository;
import com.example.bootagit_project01.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
 * @Service 어노테이션 :
 *
 * */
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository = new MemoryUserRepository();


    public String userlogin() {

        return null;
    }


//    public User JoinUser(UserDto userDTO) {
//        User user = new User();
//        user.setUsername(userDTO.getPassword());
//        user.setPassword(userDTO.getPassword());
//        user.setEmail(userDTO.getEmail());
//        return userRepository.save(user);
//    }

    // 회원가입
    public Long joinUser(User user) {
        // 같은 이름이 있는 중복 회원x
        // 코드안에 Optional 이 들어간게 예쁘지 않음으로
//            Optional<User> result = userRepository.findByName(user.getUsername());
//            result.ifPresent(u -> {

        //Optional 안에 내장 메서드 ifPresent() 만약에 값이 있으면->
        // 값을 그냥 꺼내는 것 보다 위의 예외처리 권장/ or문 권장(orElseThrow)

        validateDuplicateUser(user);    // 중복 회원 검증



        userRepository.save(user);
        return user.getUserid();
    }

    private void validateDuplicateUser(User user) {
        userRepository.findByName(user.getUsername())
                .ifPresent(u -> {
                    throw new IllegalArgumentException("이미 존재하는 회원입니다.");
                });
    }

    /*전체 회원 조회*/
    public List<User> findUsers(){
        return userRepository.findAll();
    }

    public Optional<User> findOne(Long userId){
        return userRepository.findById(userId);
    }



}
