package com.demo.springmio.Services.impl;


import com.demo.springmio.Exceptions.NotFoundException;
import com.demo.springmio.Model.Task;
import com.demo.springmio.Model.User;
import com.demo.springmio.Services.TaskService;
import com.demo.springmio.repository.TaskRepository;
import com.demo.springmio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Task with id " + id + " not found"));
    }

    @Override
    public Task createTask(Task task, Long userId) {
        // buscamos el usuario dueño de la tarea
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new NotFoundException("User with id " + userId + " not found"));

        task.setUser(user);
        // si quieres que una tarea nueva siempre empiece como no hecha:
        if (task.getDone() == null) {
            task.setDone(false);
        }

        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long id, Task taskData) {
        Task existing = taskRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Task with id " + id + " not found"));

        existing.setTitle(taskData.getTitle());
        existing.setDescription(taskData.getDescription());
        existing.setDone(taskData.getDone());
        existing.setDeadline(taskData.getDeadline());
        // No tocamos el user aquí normalmente, salvo que decidáis permitir reasignación

        return taskRepository.save(existing);
    }

    @Override
    public void deleteTask(Long id) {
        Task existing = taskRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Task with id " + id + " not found"));

        taskRepository.delete(existing);
    }

    @Override
    public List<Task> getTasksByUserId(Long userId) {
        // Recuperamos el usuario una vez para asegurar que existe
        User u = userRepository.findById(userId)
                .orElseThrow(() ->
                        new NotFoundException("User with id " + userId + " not found"));
        return taskRepository.findByUserId(userId);
    }

    @Override
    public List<Task> getPendingTasks() {
        return taskRepository.findByDone(false);
    }
}
