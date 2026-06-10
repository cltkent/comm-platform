package com.clt.toscana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Main entry point for the Communication Services Platform.
 */
@SpringBootApplication
@EnableAsync
public class CommPlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommPlatformApplication.class, args);
    }
}
