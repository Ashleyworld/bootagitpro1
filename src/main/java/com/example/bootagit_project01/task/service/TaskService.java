// TaskService.java
package com.example.bootagit_project01.task.service;

import com.example.bootagit_project01.task.dto.TaskDto;
import com.example.bootagit_project01.task.entity.Task;
import com.example.bootagit_project01.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    public List<TaskDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(task -> modelMapper.map(task, TaskDto.class))
                .collect(Collectors.toList());
    }

    public TaskDto createTask(TaskDto taskDto) {
        Task task = modelMapper.map(taskDto, Task.class);
        Task savedTask = taskRepository.save(task);
        return modelMapper.map(savedTask, TaskDto.class);
    }

    public TaskDto updateTask(Long id, TaskDto taskDto) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 아이디에 할일을 찾을 수 없어용: " + id));

        modelMapper.map(taskDto, existingTask);

        Task updatedTask = taskRepository.save(existingTask);
        return modelMapper.map(updatedTask, TaskDto.class);
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 아이디에 할일을 찾을 수 없어용: " + id));

        taskRepository.delete(task);
    }
}