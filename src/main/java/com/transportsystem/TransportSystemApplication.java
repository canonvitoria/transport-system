package com.transportsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Profile("dev")
// @Configuration
// @EnableAutoConfiguration
// @ComponentScan

public class TransportSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransportSystemApplication.class, args);
    }

}
