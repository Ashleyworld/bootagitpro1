package com.example.bootagit_project01.task.service;


import com.example.bootagit_project01.task.dto.TaskDto;
import com.example.bootagit_project01.task.entity.Task;
import com.example.bootagit_project01.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;


    public List<Task> getAllTasks() {
        return taskRepository.findAll();

    }

    public Task AddTask(TaskDto taskDto){
        Task task = Task.builder()
                .id(taskDto.getId())
                .title(taskDto.getTitle())
                .description(taskDto.getDescription())
                .status(taskDto.getStatus())
                .build();

    return taskRepository.save(task);
    }


    public Task DeleteTask(Long taskId){
        // id 로 task 객체를 조회
        Task task = taskRepository.findById(taskId);
        .orElseThrow(() -> new RuntimeException("task 에 해당하는 아이디를 찾을 수 없습니다."+taskId));

        taskRepository.delete(task);
    }
}
