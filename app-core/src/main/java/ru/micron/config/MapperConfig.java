package ru.micron.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.micron.mapper.ActorMapper;
import ru.micron.mapper.FilmMapper;
import ru.micron.mapper.RoleMapper;
import ru.micron.mapper.UserMapper;

@Configuration
public class MapperConfig {

  @Bean
  protected RoleMapper getRoleMapper() {
    return RoleMapper.INSTANCE;
  }

  @Bean
  protected UserMapper getUserMapper() {
    return UserMapper.INSTANCE;
  }

  @Bean
  protected ActorMapper getActorMapper() {
    return ActorMapper.INSTANCE;
  }

  @Bean
  protected FilmMapper getFilmMapper() {
    return FilmMapper.INSTANCE;
  }
}
