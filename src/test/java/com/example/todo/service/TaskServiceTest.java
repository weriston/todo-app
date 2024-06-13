package com.example.todo.service;

import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    public void testCreateTask() {
        Task task = new Task(null, "Description", "High", false, 1L);
        taskService.addTask(task);
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    public void testUpdateTask() {
        Task task = new Task(1L, "Updated Description", "Medium", false, 1L);
        taskService.updateTask(task);
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    public void testDeleteTask() {
        Long taskId = 1L;
        taskService.deleteTask(taskId);
        verify(taskRepository, times(1)).deleteById(taskId);
    }

    @Test
    public void testGetPendingTasks() {
        Long userId = 1L;
        String priority = "High"; // Adicione a prioridade aqui
        List<Task> mockTasks = List.of(
                new Task(1L, "Task 1", "High", false, userId),
                new Task(2L, "Task 2", "Medium", false, userId)
        );
        when(taskRepository.findByUserIdAndCompletedFalse(userId)).thenReturn(mockTasks);

        List<Task> tasks = taskService.getPendingTasks(userId, priority); // Passar a prioridade
        assertEquals(2, tasks.size());
        assertEquals("Task 1", tasks.get(0).getDescription());
        assertEquals("Task 2", tasks.get(1).getDescription());

        verify(taskRepository, times(1)).findByUserIdAndCompletedFalse(userId);
    }

}
