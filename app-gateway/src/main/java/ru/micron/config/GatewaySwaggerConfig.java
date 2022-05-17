package ru.micron.config;

import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Primary
@Configuration
@EnableSwagger2
@RequiredArgsConstructor
public class GatewaySwaggerConfig implements SwaggerResourcesProvider {

  private static final String API_DOCS_V2 = "/v3/api-docs";

  private final RouteLocator routeLocator;

  @Value("${spring.application.name}")
  private String appName;

  @Override
  public List<SwaggerResource> get() {
    return routeLocator.getRoutes()
        .stream()
        .map(this::swaggerResource)
        .collect(Collectors.toList());
  }

  @Bean
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

  private SwaggerResource swaggerResource(Route route) {
    var swaggerResource = new SwaggerResource();
    swaggerResource.setName(route.getId());
    swaggerResource.setLocation(route.getPrefix().concat(API_DOCS_V2));
    swaggerResource.setSwaggerVersion("3.0");
    return swaggerResource;
  }

  private ApiInfo getApiInfo() {
    return new ApiInfo(appName, EMPTY, "1.0", EMPTY, ApiInfo.DEFAULT_CONTACT, EMPTY, EMPTY,
        Collections.emptyList());
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
