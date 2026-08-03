package com.portmanagement.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Port Management System API")
                        .version("1.0.0")
                        .description("REST API Documentation for AI Port Management System")
                        .contact(new Contact()
                                .name("Port Management Team")
                                .email("support@portmanagement.com"))
                        .license(new License().name("Apache 2.0").url("https://springdoc.org")));
    }
}
