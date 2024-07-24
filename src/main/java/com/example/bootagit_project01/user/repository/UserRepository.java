package com.example.bootagit_project01.user.repository;

import com.example.bootagit_project01.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long userid);
    Optional<User> findByName(String username);
    List<User> findAll();
    Optional<User> deleteById(Long userid);
}
