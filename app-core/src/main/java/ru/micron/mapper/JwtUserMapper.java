package ru.micron.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.CollectionUtils;
import ru.micron.persistence.model.RoleEntity;
import ru.micron.persistence.model.User;
import ru.micron.persistence.model.User.Status;
import ru.micron.security.jwt.JwtUserDetails;

@Mapper(componentModel = "spring", imports = {Status.class})
public abstract class JwtUserMapper {

  @Mapping(target = "authorities", expression = "java(mapToGrantedAuthority(user.getRoles()))")
  @Mapping(target = "isEnabled", expression = "java(Status.ACTIVE.equals(user.getStatus()))")
  public abstract JwtUserDetails toUserDetails(User user);

  protected static List<GrantedAuthority> mapToGrantedAuthority(List<RoleEntity> userRoles) {
    if (CollectionUtils.isEmpty(userRoles)) {
      return Collections.emptyList();
    }
    return userRoles.stream()
        .map(role -> new SimpleGrantedAuthority(role.getName()))
        .collect(Collectors.toList());
  }
}
