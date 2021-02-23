package ru.micron.configs;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.micron.mapper.ActorMapper;
import ru.micron.mapper.FilmMapper;
import ru.micron.mapper.RoleMapper;
import ru.micron.mapper.UserMapper;

@Configuration
public class MapperConfig {

    @Bean
    @ConditionalOnMissingBean(RoleMapper.class)
    protected RoleMapper getRoleMapper() {
        return RoleMapper.INSTANCE;
    }

    @Bean
    @ConditionalOnMissingBean(UserMapper.class)
    protected UserMapper getUserMapper() {
        return UserMapper.INSTANCE;
    }

    @Bean
    @ConditionalOnMissingBean(ActorMapper.class)
    protected ActorMapper getActorMapper() {
        return ActorMapper.INSTANCE;
    }

    @Bean
    @ConditionalOnMissingBean(FilmMapper.class)
    protected FilmMapper getFilmMapper() {
        return FilmMapper.INSTANCE;
    }

}
