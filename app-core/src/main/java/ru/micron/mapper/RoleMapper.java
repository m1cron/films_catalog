package ru.micron.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import liquibase.util.CollectionUtil;
import org.hibernate.mapping.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;
import ru.micron.persistence.model.Role;

@Mapper(componentModel = "spring")
public abstract class RoleMapper {

  public static final RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

  @Mapping(target = "name", source = "name")
  protected abstract Role toEntity(String name);

  protected List<Role> toListOfEntities(List<String> roles) {
    if (CollectionUtils.isEmpty(roles)) {
      return Collections.emptyList();
    }
    return roles.stream().map(o -> new Role().setName(o)).collect(Collectors.toList());
  }

  protected List<String> mapToListOfDto(List<Role> roles) {
    if (CollectionUtils.isEmpty(roles)) {
      return Collections.emptyList();
    }
    return roles.stream().map(Role::getName).collect(Collectors.toList());
  }
}
