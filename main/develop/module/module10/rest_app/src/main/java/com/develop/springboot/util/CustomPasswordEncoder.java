package com.develop.springboot.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * CustomPasswordEncoder class is a custom password encoder.
 * It provides methods for encoding and matching passwords.
 * <p> It extends BCryptPasswordEncoder class, which means it will be used for encoding and matching passwords.
 * <note> Currently this class is not used in the project.
 */
public class CustomPasswordEncoder extends BCryptPasswordEncoder {

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        System.out.println("PASSWORD: " + rawPassword + super.encode(rawPassword));
        return super.matches(rawPassword, encodedPassword);
    }

}
