package com.example.bootagit_project01.user.repository;

import com.example.bootagit_project01.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}