package com.example.bootagit_project01.task.service;


import com.example.bootagit_project01.task.entity.Task;
import com.example.bootagit_project01.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;


//    public void TaskSave(Task task){
//
//    }

        public List<Task> findTask(){
            List<Task> tasks = new ArrayList<>();
            taskRepository.findAll().forEach(tasks::add);
            return tasks;
        }

    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        taskRepository.findAll().forEach(tasks::add);
        return tasks;



    }


}
