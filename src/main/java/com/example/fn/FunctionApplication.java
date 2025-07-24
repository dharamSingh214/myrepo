package com.example.fn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FunctionApplication {

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(FunctionApplication.class, args);
    }

    public static ConfigurableApplicationContext getContext() {
        return context;
    }
}
