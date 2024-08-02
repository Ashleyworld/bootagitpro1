// TaskService.java
package com.example.bootagit_project01.task.service;

import com.example.bootagit_project01.task.dto.TaskDto;
import com.example.bootagit_project01.task.entity.Task;
import com.example.bootagit_project01.task.repository.TaskRepository;
import com.example.bootagit_project01.user.user.dto.UserDto;
import com.example.bootagit_project01.user.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public List<TaskDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(this::convertToTaskDto)
                .collect(Collectors.toList());
    }

    public TaskDto createTask(TaskDto taskDto) {
        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus());
        Task savedTask = taskRepository.save(task);
        return convertToTaskDto(savedTask);
    }

    public TaskDto updateTask(Long taskid, TaskDto taskDto) {
        Task existingTask = taskRepository.findById(taskid)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 아이디에 할일을 찾을 수 없어요: " + taskid));

        existingTask.setTitle(taskDto.getTitle());
        existingTask.setDescription(taskDto.getDescription());
        existingTask.setStatus(taskDto.getStatus());

        Task updatedTask = taskRepository.save(existingTask);
        return convertToTaskDto(updatedTask);
    }


    public void deleteTask(Long taskid) {
        Task task = taskRepository.findById(taskid)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 아이디에 할일을 찾을 수 없어요: " + taskid));

        taskRepository.delete(task);
    }

    // ModelMapper 대신 직접 User 엔티티에서 UserDto로 변환하는 메서드 convertToUserDto
    private TaskDto convertToTaskDto(Task task) {
        return TaskDto.builder()
                .taskid(task.getTaskid())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .build();
    }
}