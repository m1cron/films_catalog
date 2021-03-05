package ru.micron.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.micron.model.Role;

import java.util.List;

@Mapper(
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
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
