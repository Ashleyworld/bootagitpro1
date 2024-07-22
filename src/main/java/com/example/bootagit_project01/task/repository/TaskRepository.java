package com.example.bootagit_project01.task.repository;

import com.example.bootagit_project01.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {}