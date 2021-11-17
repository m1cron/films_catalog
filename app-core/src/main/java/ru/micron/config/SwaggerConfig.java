package ru.micron.config;

import static org.apache.logging.log4j.util.Strings.EMPTY;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@RequiredArgsConstructor
public class SwaggerConfig {

  @Value("${spring.application.name}")
  private String appName;

  @Bean
  @ConditionalOnMissingBean(Docket.class)
  public Docket swagger() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("ru.micron"))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(getApiInfo())
        .securityContexts(List.of(securityContext()))
        .securitySchemes(List.of(apiKey()));
  }

  private ApiInfo getApiInfo() {
    return new ApiInfo(appName, EMPTY, "1.0",
        EMPTY, ApiInfo.DEFAULT_CONTACT, EMPTY, EMPTY, Collections.emptyList());
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder()
        .securityReferences(List.of(new SecurityReference("Token", new AuthorizationScope[0])))
        .forPaths(PathSelectors.ant("/**"))
        .build();
  }

  private ApiKey apiKey() {
    return new ApiKey("Token", AUTHORIZATION, "header");
  }
}
