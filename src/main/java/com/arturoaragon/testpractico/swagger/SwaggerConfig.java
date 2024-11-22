package com.arturoaragon.testpractico.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI().info(new Info()
                                        .title("Api REST clientes (MySQL + Spring-boot).")
                                        .version("v1.0 SNAPSHOT")
                                        .contact(new Contact()
                                                        .name("Arturo")
                                                        .email("arturoaragonarriaga@gmail.com")
                                        )
        );
    }
}