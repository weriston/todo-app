package com.example.todo.service;

import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddTask() {
        Task task = new Task();
        taskService.addTask(task);
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void testDeleteTask_Success() {
        Long taskId = 1L;
        Long userId = 1L;

        when(taskRepository.existsByIdAndUserId(taskId, userId)).thenReturn(true);

        taskService.deleteTask(taskId, userId);

        verify(taskRepository, times(1)).deleteById(taskId);
    }

    @Test
    void testDeleteTask_Failure() {
        Long taskId = 1L;
        Long userId = 1L;

        when(taskRepository.existsByIdAndUserId(taskId, userId)).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            taskService.deleteTask(taskId, userId);
        });

        assertEquals("Você não tem permissão para excluir esta tarefa", exception.getMessage());
    }

    @Test
    void testUpdateTask() {
        Task task = new Task();
        taskService.updateTask(task);
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void testCompleteTask_Success() {
        Long taskId = 1L;
        Task task = new Task();
        task.setId(taskId);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        taskService.completeTask(taskId);

        assertTrue(task.isCompleted());
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void testCompleteTask_TaskNotFound() {
        Long taskId = 1L;

        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            taskService.completeTask(taskId);
        });

        assertEquals("Task not found", exception.getMessage());
    }

    @Test
    void testGetPendingTasks_WithPriority() {
        Long userId = 1L;
        String priority = "High";
        Task task = new Task();
        task.setUserId(userId);
        task.setPriority(priority);
        task.setCompleted(false);

        when(taskRepository.findByUserIdAndPriorityAndCompletedFalse(userId, priority)).thenReturn(Arrays.asList(task));

        List<Task> tasks = taskService.getPendingTasks(userId, priority);

        assertEquals(1, tasks.size());
        assertEquals(task, tasks.get(0));
    }

    @Test
    void testGetPendingTasks_WithoutPriority() {
        Long userId = 1L;
        Task task = new Task();
        task.setUserId(userId);
        task.setCompleted(false);

        when(taskRepository.findByUserIdAndCompletedFalse(userId)).thenReturn(Arrays.asList(task));

        List<Task> tasks = taskService.getPendingTasks(userId, null);

        assertEquals(1, tasks.size());
        assertEquals(task, tasks.get(0));
    }

    @Test
    void testIsUserOwner_True() {
        Long taskId = 1L;
        Long userId = 1L;

        when(taskRepository.existsByIdAndUserId(taskId, userId)).thenReturn(true);

        assertTrue(taskService.isUserOwner(taskId, userId));
    }

    @Test
    void testIsUserOwner_False() {
        Long taskId = 1L;
        Long userId = 1L;

        when(taskRepository.existsByIdAndUserId(taskId, userId)).thenReturn(false);

        assertFalse(taskService.isUserOwner(taskId, userId));
    }
}
