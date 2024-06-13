package com.example.todo.repository;

import com.example.todo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserIdAndCompletedFalse(Long userId);
    List<Task> findByUserIdAndPriorityAndCompletedFalse(Long userId, String priority);
    boolean existsByIdAndUserId(Long id, Long userId);
}
