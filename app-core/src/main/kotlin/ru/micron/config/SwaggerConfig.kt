package ru.micron.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun openApi(): OpenAPI {
        return OpenAPI()
            .info(apiInfo())
            .components(components())
            .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
    }

    private fun components(): Components {
        return Components().addSecuritySchemes(
            "bearerAuth",
            SecurityScheme()
                .name("bearerAuth")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
        )
    }

    private fun apiInfo(): Info {
        return Info()
            .title("Film Catalog Service")
            .description("Simple films favourite service")
            .version("1.0")
            .contact(
                Contact()
                    .name("m1cron")
                    .email("m1cron4900@gmail.com")
                    .url("https://github.com/m1cron/films_catalog")
            )
    }
}