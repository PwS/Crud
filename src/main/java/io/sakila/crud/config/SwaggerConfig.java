package io.sakila.crud.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    public static final String EMPLOYEE_TAG = "Employee Service";
    public static final String DEPARTMENT_TAG = "Department Service";

    @Bean
    public OpenAPI springSakilaOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Sakila CRUD")
                        .description("Sakila CRUD")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("https://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Refference")
                        .url("https://www.google.com"));
    }
}