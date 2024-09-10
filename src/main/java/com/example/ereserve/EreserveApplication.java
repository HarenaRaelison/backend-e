package com.example.ereserve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication

public class EreserveApplication {

    public static void main(String[] args) {
        SpringApplication.run(EreserveApplication.class, args);
    }

}
