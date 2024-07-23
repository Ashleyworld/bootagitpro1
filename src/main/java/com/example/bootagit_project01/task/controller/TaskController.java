package com.example.bootagit_project01.task.controller;

import com.example.bootagit_project01.task.dto.TaskDto;
import com.example.bootagit_project01.task.entity.Task;
import com.example.bootagit_project01.task.repository.TaskRepository;
import com.example.bootagit_project01.task.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 작업 관련 REST  API를 제공하는 컨트롤러
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;
    private ModelMapper mapper;
    @Autowired
    private TaskRepository taskRepository;

    /**
     * 모든 작업을 조회하는 API
     *
     * @return 작업 DTO 리스트를 담은 ResponseEntity
     */
    public List<TaskDto> getAllTasks(TaskDto taskDto) {
        // 모든 작업 엔티티를 조회
        List<Task> tasks = taskRepository.findAll();
        // 작업 엔티티를 작업 DTO로 변환하여 리스트로 반환
        return tasks.stream()
                .map(this::taskdto)
                .collect(Collectors.toList());
    }

    /**
     * 새로운 작업을 생성하는 API
     *
     * @param taskDto 생성할 작업 DTO
     * @return 생성된 작업 DTO를 담은 ResponseEntity
     */
    @PostMapping("/create")
    public ResponseEntity<TaskDto> addTask(@RequestBody TaskDto taskDto) {

        TaskDto savedTaskDto = taskService.createTask(taskDto);
        ResponseEntity<TaskDto> task = new ResponseEntity<>(savedTaskDto, HttpStatus.CREATED);
        return task;
    }

    /**
     * 작업을 업데이트하는 API
     *
     * @param id      업데이트할 작업의 ID
     * @param taskDto 업데이트할 작업 정보를 담은 DTO
     * @return 업데이트된 작업 DTO를 담은 ResponseEntity
     */
    @PutMapping("update/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        // 작업 서비스를 통해 작업 업데이트
        TaskDto updatedTask = taskService.updateTask(id, taskDto);
        // 업데이트된 작업 DTO를 ResponseEntity에 담아 반환
        return ResponseEntity.ok(updatedTask);
    }

    /**
     * 작업을 삭제하는 API
     *
     * @param id 삭제할 작업의 ID
     * @return 빈 ResponseEntity (HTTP 상태 코드 204)
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        // 작업 서비스를 통해 작업 삭제
        taskService.deleteTask(id);
        // 빈 ResponseEntity와 함께 HTTP 상태 코드 204 (No Content) 반환
        return ResponseEntity.noContent().build();
    }
}