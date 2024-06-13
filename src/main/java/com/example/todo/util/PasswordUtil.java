package com.example.todo.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtil {
    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String hashPassword(String password) {
        return encoder.encode(password);
    }

    public static boolean checkPassword(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }

    public static boolean validatePassword(String password) {
        // Verifica se a senha atende aos critérios mínimos de segurança
        // Critérios:
        // - Pelo menos 8 caracteres
        // - Contém pelo menos uma letra maiúscula
        // - Contém pelo menos uma letra minúscula
        // - Contém pelo menos um número
        // - Contém pelo menos um caractere especial (!@#$%&*?)

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

    private static boolean isSpecialChar(char c) {
        // Verifica se o caractere é um dos caracteres especiais permitidos
        return c == '!' || c == '@' || c == '#' || c == '$' || c == '%' || c == '&' || c == '*' || c == '?';
    }
}