package ru.micron.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.micron.persistence.model.Role;

@Mapper(componentModel = "spring")
public abstract class RoleMapper {

  public static final RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

  protected abstract List<String> mapToListOfDto(List<Role> roles);

  protected String mapToString(Role role) {
    return role.getName();
  }

  protected abstract List<Role> toListOfEntities(List<String> roles);

  protected Role toEntity(String role) {
    Role roleEntity = new Role();
    roleEntity.setName(role);
    return roleEntity;
  }

}
