package com.ffm.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ForestFireMonitorBackend {

    public static void main(String[] args) {
        SpringApplication.run(ForestFireMonitorBackend.class, args);
    }
}
