package com.example.newdelivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NewDeliveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewDeliveryApplication.class, args);
    }

}
