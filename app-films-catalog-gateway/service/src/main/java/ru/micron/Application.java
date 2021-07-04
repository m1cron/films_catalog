package ru.micron;

import static ru.micron.libs.configs.SwaggerConfig.BASE_PACKAGE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication(scanBasePackages = BASE_PACKAGE)
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
