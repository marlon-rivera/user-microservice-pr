package com.pragma.user_service.domain.util;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncryptor {

    private PasswordEncryptor(){}

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }
}