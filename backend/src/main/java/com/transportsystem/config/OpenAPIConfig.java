package com.transportsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

// documentação
@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Transportes API")
                        .description("API para gestão de logística e cálculo de fretes.")
                        .contact(new Contact().name("Vitoria").email("contatovitoriacanon@gmail.com"))
                        .version("1.0.0"));
    }
}