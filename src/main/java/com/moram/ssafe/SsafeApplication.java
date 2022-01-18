package com.moram.ssafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SsafeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsafeApplication.class, args);
    }

}
