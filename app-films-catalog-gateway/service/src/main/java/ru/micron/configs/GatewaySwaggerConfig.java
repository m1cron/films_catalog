package ru.micron.configs;

import static net.logstash.logback.encoder.org.apache.commons.lang3.StringUtils.EMPTY;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Primary
@Configuration
@EnableSwagger2
@RequiredArgsConstructor
public class GatewaySwaggerConfig implements SwaggerResourcesProvider {

  private static final String API_DOCS_V2 = "/v2/api-docs";

  private final RouteLocator routeLocator;

  @Value("${spring.application.name}")
  private String appName;

  @Override
  public List<SwaggerResource> get() {
    List<SwaggerResource> resources = new ArrayList<>();
    resources.add(swaggerResource(new Route(appName, EMPTY, EMPTY, EMPTY, false, null)));
    resources.addAll(routeLocator.getRoutes().stream()
        .map(this::swaggerResource)
        .collect(Collectors.toList()));
    return resources;
  }

  private SwaggerResource swaggerResource(Route route) {
    var swaggerResource = new SwaggerResource();
    swaggerResource.setName(route.getId());
    swaggerResource.setLocation(route.getPrefix().concat(API_DOCS_V2));
    swaggerResource.setSwaggerVersion("2.0");
    return swaggerResource;
  }
}
