package com.example.bootagit_project01.user.repository;

import com.example.bootagit_project01.user.entity.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryUserRepository implements UserRepository{

    private static Map<Long, User> store = new HashMap<>();
    private static long sequence = 0L;

    // 시퀀스 : 0, 1, 2 라는 키값을 생성해주는 애
    // sequence는 클래스의 정적 필드로 선언되어 있으므로,
    // 클래스가 로드될 때 한 번만 초기화되고 클래스의 모든 인스턴스가
    // 이 값을 공유합니다. 이는 특정 클래스에 대한 상태를 유지하는 데 유용합니다.

    @Override
    public User save(User user) {
        user.setUserid(++sequence);
        // setId 할때 sequence 값을 올려줌

        store.put(user.getUserid(), user);
        // 위의 Map store 에 유저 관련 map 집합

        return user;
    }

    @Override
    public Optional<User> findById(Long userid) {
        return Optional.ofNullable(store.get(userid));
        // null 이 여도 store에서 진행한다
    }

    @Override
    public Optional<User> findByName(String username) {
        return store.values().stream() // 루프를 돌림
                .filter(member -> member.getUsername().equals(username))
                // member.getname이 파라미터로 넘어온 name과 같은지 확인,
                // 같은경우에만 필터이

                .findAny();
                // 찾으면 반환하라
        // 끝까지 돌렸는데 없으면 optional에 null이 포함되서 반환
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
        // 위에 store라는 맵에 user가 담겨있지만
        // 반환 타입이 list이므로 arraylist로 반환
    }

    @Override
    public Optional<User> deleteById(User user) {
        return Optional.ofNullable(store.remove(user.getUserid()));
    }


//    @Override
//    public Optional<User> deleteById(Long userid) {
//        return Optional.ofNullable(store.get(userid));
//        // null 이 여도 store에서 진행한다
//    }


    public void clearStore() {
        store.clear();
    }
}
