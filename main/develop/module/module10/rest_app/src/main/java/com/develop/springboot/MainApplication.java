package com.develop.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportRuntimeHints;

/**
 * MainApplication class is a main application class.
 * It provides methods for running the application.
 * <p> It is annotated with @SpringBootApplication, which means it will be used for running the application.
 * <p> It is annotated with @ImportRuntimeHints, which means it will be used for importing runtime hints from MainRuntimeHints class.
 */
@SpringBootApplication
//@ImportRuntimeHints(MainRuntimeHints.class)
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}