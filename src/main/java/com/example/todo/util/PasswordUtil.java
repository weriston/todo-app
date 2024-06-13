package com.example.todo.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtil {
    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();
    private static final String INVALID_PASSWORD_MESSAGE =
            "Senha não corresponde aos requisitos mínimos de segurança. " +
                    "A senha deve conter letras maiúsculas, minúsculas, números e pelo menos 1 caractere especial EX:!@#$%&*";

    public static String hashPassword(String password) {
        return encoder.encode(password);
    }

    public static boolean checkPassword(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }

    public static boolean validatePassword(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasNumber = false;
        boolean hasSpecialChar = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowercase = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            } else if (isSpecialChar(c)) {
                hasSpecialChar = true;
            }
        }

        return hasUppercase && hasLowercase && hasNumber && hasSpecialChar;
    }

    public static String getInvalidPasswordMessage() {
        return INVALID_PASSWORD_MESSAGE;
    }

    private static boolean isSpecialChar(char c) {
        return c == '!' || c == '@' || c == '#' || c == '$' || c == '%' || c == '&' || c == '*' || c == '?';
    }
}
