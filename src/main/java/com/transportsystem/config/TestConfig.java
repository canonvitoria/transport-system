package com.transportsystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class TestConfig {
    @Bean
    public SDKAWS sdkAWS() {
        return new SDKAWS();
    }
}