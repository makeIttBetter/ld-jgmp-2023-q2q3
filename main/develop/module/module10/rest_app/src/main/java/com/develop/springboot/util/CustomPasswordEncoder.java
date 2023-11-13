package com.develop.springboot.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CustomPasswordEncoder extends BCryptPasswordEncoder {

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        System.out.println("PASSWORD: " + rawPassword + super.encode(rawPassword));
        return super.matches(rawPassword, encodedPassword);
    }

}
