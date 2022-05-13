package ru.micron.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.micron.dto.RegisterUserDto;
import ru.micron.persistence.model.User;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public abstract class UserMapper {

  public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  public abstract RegisterUserDto toDto(User user);

  public abstract User toEntity(RegisterUserDto userDTO);
}
