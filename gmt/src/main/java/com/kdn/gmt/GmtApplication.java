package com.kdn.gmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.kdn")
@EnableJpaRepositories(basePackages = "com.kdn.core.repository")
@EntityScan("com.kdn.core.domain")
public class GmtApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmtApplication.class, args);
    }

}
