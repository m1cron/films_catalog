package ru.micron.config

import org.apache.commons.lang.StringUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.netflix.zuul.filters.Route
import org.springframework.cloud.netflix.zuul.filters.RouteLocator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.http.HttpHeaders
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.*
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger.web.SwaggerResource
import springfox.documentation.swagger.web.SwaggerResourcesProvider
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Primary
@Configuration
@EnableSwagger2
class GatewaySwaggerConfig(
    private val routeLocator: RouteLocator
) : SwaggerResourcesProvider {

    @Value("\${spring.application.name}")
    lateinit var appName: String

    override fun get(): List<SwaggerResource> {
        return routeLocator.routes.map { route: Route -> swaggerResource(route) }
    }

    @Bean
    fun swagger(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("ru.micron"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo)
            .securityContexts(listOf(securityContext()))
            .securitySchemes(listOf(apiKey()))
    }

    private fun swaggerResource(route: Route): SwaggerResource {
        val swaggerResource = SwaggerResource()
        swaggerResource.name = route.id
        swaggerResource.location = route.prefix + "/v3/api-docs"
        swaggerResource.swaggerVersion = "3.0"
        return swaggerResource
    }

    private val apiInfo: ApiInfo
        get() = ApiInfo(
            appName,
            StringUtils.EMPTY,
            "1.0",
            StringUtils.EMPTY,
            ApiInfo.DEFAULT_CONTACT,
            StringUtils.EMPTY,
            StringUtils.EMPTY,
            emptyList<VendorExtension<*>>()
        )

    private fun securityContext(): SecurityContext {
        return SecurityContext.builder()
            .securityReferences(
                listOf(
                    SecurityReference(
                        "Token",
                        arrayOfNulls<AuthorizationScope>(0)
                    )
                )
            )
            .forPaths(PathSelectors.ant("/**"))
            .build()
    }

    private fun apiKey(): ApiKey {
        return ApiKey("Token", HttpHeaders.AUTHORIZATION, "header")
    }
}