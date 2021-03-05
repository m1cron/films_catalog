package ru.micron;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = "ru.micron")
@EnableJpaAuditing
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
