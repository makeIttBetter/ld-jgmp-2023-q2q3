package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * A simple command line runner that prints "Hello World!".
 * <p> Command line runner is a simple interface with a single method that is called just before SpringApplication.run(…) completes.
 * <p> The order of execution of the command line runners depends on the order of the beans in the application context.
 * <p> The command line runner is a good place to run some quick tests to make sure that everything is wired up correctly.
 * <p> The command line runner is also a good place to run some database scripts to set up the database for the application.</p>
 */
@Component
public class HelloWorldRunner implements CommandLineRunner {

    /**
     * Prints "Hello World!".
     *
     * @param args Command line arguments.
     *             Transfers the command line arguments to the run method.
     * @throws Exception If something goes wrong.
     */
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello World!");
    }
}
