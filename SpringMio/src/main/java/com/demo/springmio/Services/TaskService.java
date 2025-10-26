package com.demo.springmio.Services;

import com.demo.springmio.Model.Task;

import java.util.List;

public interface TaskService {

    List<Task> getAllTasks();

    Task getTaskById(Long id);

    Task createTask(Task task, Long userId);

    Task updateTask(Long id, Task taskData);

    void deleteTask(Long id);

    List<Task> getTasksByUserId(Long userId);

    List<Task> getPendingTasks(); // por ejemplo, done = false
}
