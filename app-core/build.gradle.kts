import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("kapt") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21"
}

extra.apply {
    set("springCloudVersion", "2021.0.1")
    set("openApi", "1.6.8")
    set("jjwt", "0.9.1")
    set("mapstruct", "1.5.2.Final")
}

group = "ru.micron"
version = "1.0"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Spring & Spring Cloud
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // Swagger
    implementation("org.springdoc:springdoc-openapi-ui:${property("openApi")}")

    // Database
    implementation("org.postgresql:postgresql")
    implementation("org.liquibase:liquibase-core")

    // JWT
    implementation("io.jsonwebtoken:jjwt:${property("jjwt")}")

    // Mapstruct
    implementation("org.mapstruct:mapstruct:${property("mapstruct")}")
    kapt("org.mapstruct:mapstruct-processor:${property("mapstruct")}")

    // Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.h2database:h2")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
