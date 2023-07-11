package org.example2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class LiquibaseDemoApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(LiquibaseDemoApplication.class);
        Map<String, Object> props = new HashMap<>();
        props.put("spring.config.name", "app-liquibase");
        app.setDefaultProperties(props);
        app.run(args);
    }

}
