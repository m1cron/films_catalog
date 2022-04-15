package ru.micron.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.micron.dto.UserDto;
import ru.micron.model.User;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public abstract class UserMapper {

  public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  public abstract UserDto toDto(User user);

  public abstract User toEntity(UserDto userDTO);
}
