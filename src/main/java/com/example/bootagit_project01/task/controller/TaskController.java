package com.example.bootagit_project01.task.controller;

import com.example.bootagit_project01.task.dto.TaskDto;
import com.example.bootagit_project01.task.entity.Task;
import com.example.bootagit_project01.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class TaskController {

    @Autowired
    private TaskService taskService;


//    @GetMapping("/")
//    public String taskhome() {
//        return "view/list";
//    }

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


}
