package net.sonerapp.product.doc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI openAPI() {
        return new OpenAPI().info(new Info()
                .title("Product Microservice API")
                .version("1.0.0")
                .description(
                        "API documentation for the Product Microservice, providing endpoints for managing products.")
                .contact(new Contact()
                        .email("support@sonerapp.net")
                        .url("https://sonerapp.net")
                        .name("SonerApp Support"))
                .termsOfService("https://sonerapp.net/terms")
                .license(new License()
                        .name("Apache 2.0")
                        .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }
}
