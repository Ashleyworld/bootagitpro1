package com.example.bootagit_project01.user.service;

import com.example.bootagit_project01.user.user.entity.User;
import com.example.bootagit_project01.user.user.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserServiceTest {

    UserService userService;
    MemoryUserRepository userRepository;

    @AfterEach
    public void afterEach(){
        userRepository.clearStore();
    }

    @BeforeEach
    public void beforeEach(){
        userRepository = new MemoryUserRepository();
        userService = new UserService(userRepository);
    }


    @Test
    void userlogin() {

    }

    @Test
    void joinUser() {
        // given    뭔가 주어졌는데, 이 데이터를 기반으로 한다
        User user = new User();
        user.setUsername("hello");

        // when     이거를 실행했을때, 이걸 검증하는구나
        Long saveId = userService.joinUser(user);

        // then     결과가 이게 나와야해, 검증부
        User findUser = userService.findOne(saveId).get();
        assertThat(user.getUsername()).isEqualTo(findUser.getUsername());
    }

    @Test
    void findUsers() {
    }

    @Test
    void findOne() {
    }
}