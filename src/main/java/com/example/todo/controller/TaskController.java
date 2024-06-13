package com.example.todo.controller;

import com.example.todo.model.Task;
import com.example.todo.service.TaskService;
import com.example.todo.model.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<String> addTask(@RequestBody Task task, Authentication authentication) {
        Long userId = ((CustomUserDetails) authentication.getPrincipal()).getId();
        task.setUserId(userId);
        taskService.addTask(task);
        return ResponseEntity.ok("Task adicionada com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id, Authentication authentication) {
        Long userId = ((CustomUserDetails) authentication.getPrincipal()).getId();
        if (!taskService.isUserOwner(id, userId)) {
            return ResponseEntity.status(403).body("Você não tem permissão para excluir esta tarefa");
        }
        taskService.deleteTask(id);
        return ResponseEntity.ok("Task excluída com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTask(@PathVariable Long id, @RequestBody Task task, Authentication authentication) {
        Long userId = ((CustomUserDetails) authentication.getPrincipal()).getId();
        if (!taskService.isUserOwner(id, userId)) {
            return ResponseEntity.status(403).body("Você não tem permissão para atualizar esta tarefa");
        }
        task.setId(id);
        taskService.updateTask(task);
        return ResponseEntity.ok("Task atualizada com sucesso!");
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<String> completeTask(@PathVariable Long id, Authentication authentication) {
        Long userId = ((CustomUserDetails) authentication.getPrincipal()).getId();
        if (!taskService.isUserOwner(id, userId)) {
            return ResponseEntity.status(403).body("Você não tem permissão para marcar esta tarefa como concluída");
        }
        taskService.completeTask(id);
        return ResponseEntity.ok("Task marcada como concluída com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<Task>> getPendingTasks(@RequestParam(required = false) String priority, Authentication authentication) {
        Long userId = ((CustomUserDetails) authentication.getPrincipal()).getId();
        List<Task> tasks = taskService.getPendingTasks(userId, priority);
        return ResponseEntity.ok(tasks);
    }
}
