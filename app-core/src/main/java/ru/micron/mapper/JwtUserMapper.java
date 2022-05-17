package ru.micron.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.micron.persistence.model.RoleEntity;
import ru.micron.persistence.model.User;
import ru.micron.persistence.model.User.Status;
import ru.micron.security.UserDetails;

@Mapper(componentModel = "spring", imports = {Status.class})
public abstract class JwtUserMapper {

  @Mapping(target = "authorities", expression = "java(mapToGrantedAuthority(user.getRoles()))")
  @Mapping(target = "isEnabled", expression = "java(Status.ACTIVE.equals(user.getStatus()))")
  public abstract UserDetails toUserDetails(User user);

  protected static List<GrantedAuthority> mapToGrantedAuthority(Set<RoleEntity> userRoles) {
    return userRoles.stream()
        .map(role -> new SimpleGrantedAuthority(role.getName().name()))
        .collect(Collectors.toList());
  }
}
