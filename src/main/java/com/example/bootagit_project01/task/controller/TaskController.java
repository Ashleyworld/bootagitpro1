package com.example.bootagit_project01.task.controller;

import com.example.bootagit_project01.task.dto.TaskDto;
import com.example.bootagit_project01.task.entity.Task;
import com.example.bootagit_project01.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String AddTask(TaskDto taskDto) {
        Task addedTask = taskService.AddTask(taskDto);


        return "ResponseEntity.ok(tasks)";
    }

    @PostMapping("/delete")
    public String deleteTask(TaskDto taskDto, RedirectAttributes redirectAttributes) {
        Task deletedTask = taskService.DeleteTask(taskDto);

        // 리다이렉트 시 메시지를 전달합니다.
        redirectAttributes.addFlashAttribute("message", "Task deleted successfully with ID: " + deletedTask.getId());

        return "redirect:/list";
    }



}
