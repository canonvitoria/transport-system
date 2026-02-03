package com.transportsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TransportSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransportSystemApplication.class, args);

        System.out.println("Aplicação Iniciada! 🚀");
    }

}