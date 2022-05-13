package ru.micron.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

@Configurable
public class SwaggerConfig {

  @Bean
  public OpenAPI openApi() {
    return new OpenAPI().info(apiInfo());
  }

  private Info apiInfo() {
    return new Info()
        .title("Film Catalog Service")
        .description("Simple films favourite service")
        .version("1.0")
        .contact(
            new Contact()
                .name("m1cron")
                .email("m1cron4900@gmail.com")
                .url("https://github.com/m1cron/films_catalog"));
  }
}
