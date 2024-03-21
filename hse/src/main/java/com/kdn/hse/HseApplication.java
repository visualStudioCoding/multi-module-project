package com.kdn.hse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.kdn")
public class HseApplication {

    public static void main(String[] args) {
        SpringApplication.run(HseApplication.class, args);
    }

}
