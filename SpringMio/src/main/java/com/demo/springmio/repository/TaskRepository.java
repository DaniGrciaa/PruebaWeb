package com.demo.springmio.repository;

import com.demo.springmio.Model.Task;
import com.demo.springmio.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    // todas las tareas de un usuario concreto
    List<Task> findByUserId(Long userId);

    // todas las tareas según si están hechas o no hechas
    List<Task> findByDone(boolean done);
}
