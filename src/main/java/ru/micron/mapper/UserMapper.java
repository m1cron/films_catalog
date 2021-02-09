package ru.micron.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.micron.dto.UserDTO;
import ru.micron.model.User;

@Mapper(uses = { RoleMapper.class },
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
public abstract class UserMapper {

    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "favorite_films", source = "favoriteFilms")
    public abstract UserDTO toDto(User user);

}
