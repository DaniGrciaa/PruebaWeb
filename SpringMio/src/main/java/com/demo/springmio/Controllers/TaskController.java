package com.demo.springmio.Controllers;


import com.demo.springmio.Model.Task;
import com.demo.springmio.Services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public List<Task> getAll() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getOne(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Task> getByUser(@PathVariable Long userId) {
        return taskService.getTasksByUserId(userId);
    }

    @GetMapping("/pending")
    public List<Task> getPending() {
        return taskService.getPendingTasks();
    }

    @PostMapping("/user/{userId}")
    public Task create(
            @PathVariable Long userId,
            @RequestBody Task taskBody
    ) {
        return taskService.createTask(taskBody, userId);
    }

    @PutMapping("/{id}")
    public Task update(
            @PathVariable Long id,
            @RequestBody Task taskBody
    ) {
        return taskService.updateTask(id, taskBody);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
