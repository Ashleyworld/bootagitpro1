package com.example.bootagit_project01.user.user.repository;

import com.example.bootagit_project01.user.user.entity.User;

import java.util.List;
import java.util.Optional;


public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long userid);
    Optional<User> findByName(String username);
    List<User> findAll();
    Optional<User> deleteById(User user);
}
