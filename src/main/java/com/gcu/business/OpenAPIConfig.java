package com.gcu.business;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.Contact;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Speed-E-Eats REST API",
        version = "1.0",
        description = "Secure REST API for viewing and managing product data.",
        contact = @Contact(name = "CST-339 CLC Group 1")
    )
)
public class OpenAPIConfig { }
