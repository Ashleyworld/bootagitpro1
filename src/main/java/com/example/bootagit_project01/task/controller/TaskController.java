package com.example.bootagit_project01.task.controller;

import com.example.bootagit_project01.task.dto.TaskDto;
import com.example.bootagit_project01.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 작업  관련 REST API를 제공하는 컨트롤러
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * 모든 작업을 조회하는 API
     *
     * @return 작업 DTO 리스트를 담은 ResponseEntity
     */
    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        // 작업 서비스를 통해 모든 작업 조회
        List<TaskDto> tasks = taskService.getAllTasks();
        // 조회된 작업 리스트를 ResponseEntity에 담아 반환
        return ResponseEntity.ok(tasks);
    }

    /**
     * 새로운 작업을 생성하는 API
     *
     * @param taskDto 생성할 작업 DTO
     * @return 생성된 작업 DTO를 담은 ResponseEntity
     */
    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        // 작업 서비스를 통해 새로운 작업 생성
        TaskDto createdTask = taskService.createTask(taskDto);
        // 생성된 작업 DTO를 ResponseEntity에 담아 반환
        return ResponseEntity.ok(createdTask);
    }

    /**
     * 작업을 업데이트하는 API
     *
     * @param id      업데이트할 작업의 ID
     * @param taskDto 업데이트할 작업 정보를 담은 DTO
     * @return 업데이트된 작업 DTO를 담은 ResponseEntity
     */
    @PutMapping("/{id}")
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
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        // 작업 서비스를 통해 작업 삭제
        taskService.deleteTask(id);
        // 빈 ResponseEntity와 함께 HTTP 상태 코드 204 (No Content) 반환
        return ResponseEntity.noContent().build();
    }
}