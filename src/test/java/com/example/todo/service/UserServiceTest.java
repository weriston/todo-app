package com.example.todo.service;

import com.example.todo.model.User;
import com.example.todo.repository.UserRepository;
import com.example.todo.util.PasswordUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setName("João");
        user.setEmail("joao@example.com");
        user.setPassword("Password@123");

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setName(user.getName());
        savedUser.setEmail(user.getEmail());
        savedUser.setPassword(PasswordUtil.hashPassword(user.getPassword()));

        when(userRepository.findByEmail(user.getEmail())).thenReturn(null);
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        User createdUser = userService.createUser(user);

        assertNotNull(createdUser.getId());
        assertEquals(user.getName(), createdUser.getName());
        assertEquals(user.getEmail(), createdUser.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testCreateUserWithExistingEmail() {
        User user = new User();
        user.setName("João");
        user.setEmail("joao@example.com");
        user.setPassword("Password@123");

        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);

        assertThrows(IllegalArgumentException.class, () -> userService.createUser(user));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void testCreateUserWithInvalidPassword() {
        User user = new User();
        user.setName("João");
        user.setEmail("joao@example.com");
        user.setPassword("invalidpassword");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> userService.createUser(user));
        assertEquals(PasswordUtil.getInvalidPasswordMessage(), exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }
}
