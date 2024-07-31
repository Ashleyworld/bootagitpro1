package com.example.bootagit_project01.user.repository;


import com.example.bootagit_project01.user.user.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryUserRepositoryTest {

    MemoryUserRepository repository = new MemoryUserRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }
    // 하나의 테스트가 끝날때마다 저장소를 싹 비워주는 메서드
    // 다른 테스트랑 맞물려서 테스트 되지 않는다./ 공용데이터가 겹치지 않는다.

    @Test
    public void save() {
        User user = new User();
        user.setUsername("spring");

        repository.save(user);

        User result = repository.findById(user.getUserid()).get();
        // Optional 이라 뒤에 get() 한개 더 붙여줘야함

        System.out.println("result = " + (result == user));
        Assertions.assertEquals(user, result);
        assertThat(user).isEqualTo(result);

        // user와 result 결과값이 맞냐?
    }

    @Test
    public void findByName() {
        User user1 = new User();
        user1.setUsername("spring1");
        repository.save(user1);

        User user2 = new User();
        user2.setUsername("srping2");
        repository.save(user2);

        User result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(user1);


    }

    @Test
    public void findAll(){
        User user1 = new User();
        user1.setUsername("spring1");
        repository.save(user1);

        User user2 = new User();
        user2.setUsername("spring2");
        repository.save(user2);

        List<User> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }



}

