package ru.micron.mapper;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;
import ru.micron.persistence.model.RoleEntity;

@Mapper(componentModel = "spring", imports = {UUID.class, StandardCharsets.class})
public abstract class RoleMapper {

  public static final RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

  @Mapping(target = "id", expression = "java(UUID.nameUUIDFromBytes(name.name().getBytes(StandardCharsets.UTF_8)))")
  @Mapping(target = "name", source = "name")
  @Mapping(target = "users", ignore = true)
  protected abstract RoleEntity toEntity(RoleEntity.ERole name);

  public List<String> toList(List<RoleEntity> roles) {
    if (CollectionUtils.isEmpty(roles)) {
      return Collections.emptyList();
    }
    return roles.stream().map(o -> o.getName().name()).collect(Collectors.toList());
  }
}
