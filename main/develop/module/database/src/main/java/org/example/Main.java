package org.example;


import org.example.frameworks.SpringConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
class Main {

    public static void main(String[] args) {
        SpringApplication.run(SpringConfig.class, args);
    }

}
