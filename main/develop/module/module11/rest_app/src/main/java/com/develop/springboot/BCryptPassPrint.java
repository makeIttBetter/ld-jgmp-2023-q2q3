package com.develop.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptPassPrint implements CommandLineRunner {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        String password = "password";
        System.out.println("Password: " + password);
        System.out.println("BCrypt: " + passwordEncoder.encode(password));
    }
}
