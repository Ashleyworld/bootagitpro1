package com.example.bootagit_project01.task.service;

import com.example.bootagit_project01.task.dto.TaskDto;
import com.example.bootagit_project01.task.entity.Task;
import com.example.bootagit_project01.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    /**
     * 모든 작업을 조회하는 메서드
     *
     * @return 작업 DTO 리스트
     */
    public List<TaskDto> getAllTasks() {
        // 모든 작업 엔티티를 조회
        List<Task> tasks = taskRepository.findAll();
        // 작업 엔티티를 작업 DTO로 변환하여 리스트로 반환
        return tasks.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * 새로운 작업을 생성하는 메서드
     *
     * @param taskDto 생성할 작업 DTO
     * @return 생성된 작업 DTO
     */
    public TaskDto createTask(TaskDto taskDto) {
        // 작업 DTO를 작업 엔티티로 변환
        Task task = convertToEntity(taskDto);
        // 작업 엔티티를 데이터베이스에 저장
        Task savedTask = taskRepository.save(task);
        // 저장된 작업 엔티티를 작업 DTO로 변환하여 반환
        return convertToDto(savedTask);
    }

    /**
     * 작업 엔티티를 작업 DTO로 변환하는 메서드
     *
     * @param task 작업 엔티티
     * @return 작업 DTO
     */
    private TaskDto convertToDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setTitle(task.getTitle());
        taskDto.setDescription(task.getDescription());
        taskDto.setStatus(task.getStatus());
        return taskDto;
    }

    /**
     * 작업 DTO를 작업 엔티티로 변환하는 메서드
     *
     * @param taskDto 작업 DTO
     * @return 작업 엔티티
     */
    private Task convertToEntity(TaskDto taskDto) {
        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus());
        return task;
    }

    /**
     * 작업을 업데이트하는 메서드
     *
     * @param id      업데이트할 작업의 ID
     * @param taskDto 업데이트할 작업 정보를 담은 DTO
     * @return 업데이트된 작업 DTO
     * @throws IllegalArgumentException 해당 ID의 작업이 존재하지 않을 경우
     */
    public TaskDto updateTask(Long id, TaskDto taskDto) {
        // 업데이트할 작업을 ID로 조회
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 아이디에 할일을 찾을 수 없어용: " + id));

        // 조회된 작업의 정보를 업데이트
        existingTask.setTitle(taskDto.getTitle());
        existingTask.setDescription(taskDto.getDescription());
        existingTask.setStatus(taskDto.getStatus());

        // 업데이트된 작업을 데이터베이스에 저장
        Task updatedTask = taskRepository.save(existingTask);
        // 저장된 작업 엔티티를 작업 DTO로 변환하여 반환
        return convertToDto(updatedTask);
    }

    /**
     * 작업을 삭제하는 메서드
     *
     * @param id 삭제할 작업의 ID
     * @throws IllegalArgumentException 해당 ID의 작업이 존재하지 않을 경우
     */
    public void deleteTask(Long id) {
        // 삭제할 작업을 ID로 조회
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 아이디에 할일을 찾을 수 없어용: " + id));

        // 작업을 삭제
        taskRepository.delete(task);
    }
}