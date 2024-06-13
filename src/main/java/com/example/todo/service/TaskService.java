package com.example.todo.service;

import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addTask(Task task) {
        taskRepository.save(task);
    }

    public void deleteTask(Long id, Long userId) {
        if (!taskRepository.existsByIdAndUserId(id, userId)) {
            throw new IllegalArgumentException("Você não tem permissão para excluir esta tarefa");
        }
        taskRepository.deleteById(id);
    }

    public void updateTask(Task task) {
        taskRepository.save(task);
    }

    public void completeTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setCompleted(true);
        taskRepository.save(task);
    }

    public List<Task> getPendingTasks(Long userId, String priority) {
        if (priority != null) {
            return taskRepository.findByUserIdAndPriorityAndCompletedFalse(userId, priority);
        } else {
            return taskRepository.findByUserIdAndCompletedFalse(userId);
        }
    }

    public boolean isUserOwner(Long taskId, Long userId) {
        return taskRepository.existsByIdAndUserId(taskId, userId);
    }
}
