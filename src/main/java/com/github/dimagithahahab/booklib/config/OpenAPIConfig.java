package com.github.dimagithahahab.booklib.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Library management")
                        .description("Books and authors management REST API")
                        .contact(new Contact()
                                .email("kdmitry-xyz@outlook.com")
                                .url("https://github.com/DimaGitHahahab/book-lib"))
                        .version(appVersion)
                );
    }
}