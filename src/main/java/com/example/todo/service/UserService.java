package com.example.todo.service;

import com.example.todo.model.User;
import com.example.todo.repository.UserRepository;
import com.example.todo.util.PasswordUtil;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        if (!PasswordUtil.validatePassword(user.getPassword())) {
            throw new IllegalArgumentException("Senha inválida");
        }

        user.setPassword(PasswordUtil.hashPassword(user.getPassword()));
        return userRepository.save(user);
    }
}