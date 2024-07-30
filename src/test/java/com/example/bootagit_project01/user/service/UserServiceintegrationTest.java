package com.example.bootagit_project01.user.service;

import com.example.bootagit_project01.user.user.dto.UserDto;
import com.example.bootagit_project01.user.user.entity.User;
import com.example.bootagit_project01.user.user.repository.UserRepository;
import com.example.bootagit_project01.user.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class UserServiceintegrationTest {

    @Autowired UserService userService;
    @Autowired UserRepository userRepository;


    @Test
    void userlogin() {

    }

    @Test
    void joinUser() {
        // given    뭔가 주어졌는데, 이 데이터를 기반으로 한다
        UserDto userDto = new UserDto().builder()
                .username("hello")
                .password("1234")
                .email("hello@hi.com")
                .build();

        // when     이거를 실행했을때, 이걸 검증하는구나

        UserDto saveDto = userService.joinUser(userDto);

        // then     결과가 이게 나와야해, 검증부
//        User findUser = userService.findOne(saveId).get();
        // 윗줄 때문에 생략해도 됨.

        assertThat(userDto.getUsername())
                .isEqualTo(userService.findUserName(userDto.getUsername()));
    }

//    @Test
//    String findUsersByname() {
//        // given
//
//        // when
//        List<User> findUsers = userService.findUsers("hello");
//
//        // then
//
//        return null;
//    }

    /*
        public List<User> findUsers(){
        return userRepository.findAll();
    }

     */

    @Test
    void deleteUser() {
        // given
        Long userid = 1L;

        // when
        userService.deleteUser(userid);

        // then
        User userDto = userService.findOne(userid).orElse(null);



    }
    /*
        public void deleteUser(Long userid){
        User user = userRepository.findById(userid)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 아이디가 없습니다."));

        userRepository.deleteById(user);
    }

     */

    @Test
    void findOne() {
    }
}