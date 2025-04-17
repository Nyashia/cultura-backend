package com.cultura.controller;

import com.cultura.model.Task;
import com.cultura.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks") 
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/create")
    public Task createTask(@RequestBody Task task){
        return taskRepository.save(task);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @PutMapping("/{id}/complete")
    public Task completeTask(@PathVariable Long id) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setCompleted(true);
        return taskRepository.save(task);
    }


}
