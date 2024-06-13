package com.example.todo.util;

import com.example.todo.util.PasswordUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtilTest {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "password";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("Encoded password: " + encodedPassword);

        String rawPassword2 = "password";
        boolean isMatch = PasswordUtil.checkPassword(rawPassword2, encodedPassword);
        System.out.println("Password match: " + isMatch);
    }
}