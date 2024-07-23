package com.example.bootagit_project01.task.controller;

import com.example.bootagit_project01.task.dto.TaskDto;
import com.example.bootagit_project01.task.entity.Task;
import com.example.bootagit_project01.task.service.TaskService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8060")
@RestController
@RequestMapping("api")
public class TaskController {

    @Autowired
    private TaskService taskService;

//    @GetMapping("/list")
//    public String list(Task task) {
//        List<Task> tasks = taskService.findTask();
//        tasks.contains("tasks");
//        return "view/list";
//    }

    @GetMapping("/list")
    public ResponseEntity<List<Task>> getAllTasks(){
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @PostMapping("/add")
    public String addTask(@RequestBody TaskDto taskDto, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "User not authenticated";
        }
        taskDto.setUserId(userId);  // 세션에서 가져온 userId를 TaskDto에 설정

        taskService.AddTask(taskDto);

        return "Task added successfully";
    }

    @DeleteMapping("/delete/{taskid}")
    public String deleteTask(@PathVariable("taskid") Long taskid) {
        taskService.DeleteTask(taskid);

        return "ResponseEntity.ok(tasks)";
    }



}
