package ru.micron.mapper;

import io.micrometer.core.instrument.util.StringUtils;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.micron.dto.UserDto;
import ru.micron.persistence.model.RoleEntity;
import ru.micron.persistence.model.User;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.FIELD,
    uses = {RoleMapper.class},
    imports = {UUID.class, Set.class, RoleEntity.class,
        StringUtils.class, LocalDateTime.class}
)
public abstract class UserMapper {

  public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  @Autowired
  protected RoleMapper roleMapper;

  @Autowired
  protected PasswordEncoder encoder;

  @Mapping(target = "password", ignore = true)
  public abstract UserDto toDto(User user);

  @Mapping(target = "created", expression = "java(LocalDateTime.now())")
  @Mapping(target = "removeFromFavourite", ignore = true)
  @Mapping(target = "favouriteFilms", ignore = true)
  @Mapping(target = "uuid", expression = "java(UUID.randomUUID())")
  @Mapping(target = "roles", expression = "java(Set.of(roleMapper.toEntity(RoleEntity.ERole.ROLE_USER)))")
  @Mapping(target = "status", expression = "java(User.Status.ACTIVE)")
  @Mapping(target = "password", expression = "java(encoder.encode(user.getPassword()))")
  public abstract User register(UserDto user);

  @Mapping(target = "removeFromFavourite", ignore = true)
  @Mapping(target = "uuid", source = "before.uuid")
  @Mapping(target = "email", expression = "java(StringUtils.isNotBlank(dto.getEmail()) ? dto.getEmail() : before.getEmail())")
  @Mapping(target = "username", expression = "java(StringUtils.isNotBlank(dto.getUsername()) ? dto.getUsername() : before.getUsername())")
  @Mapping(target = "password", expression = "java(StringUtils.isNotBlank(dto.getPassword()) ? encoder.encode(dto.getPassword()) : before.getPassword())")
  @Mapping(target = "firstName", expression = "java(StringUtils.isNotBlank(dto.getFirstName()) ? dto.getFirstName() : before.getFirstName())")
  @Mapping(target = "lastName", expression = "java(StringUtils.isNotBlank(dto.getLastName()) ? dto.getLastName() : before.getLastName())")
  public abstract User edit(User before, UserDto dto);
}
