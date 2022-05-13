package ru.micron.security.jwt;

import java.util.Collections;
import liquibase.util.CollectionUtil;
import org.hibernate.mapping.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.CollectionUtils;
import ru.micron.persistence.model.RoleEntity;
import ru.micron.persistence.model.Status;
import ru.micron.persistence.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

  public static JwtUser create(User user) {
    return new JwtUser(
        user.getUuid(),
        user.getUsername(),
        user.getFirstName(),
        user.getLastName(),
        user.getEmail(),
        user.getPassword(),
        mapToGrantedAuthority(user.getRoles()),
        user.getStatus().equals(Status.ACTIVE));
  }

  public static List<GrantedAuthority> mapToGrantedAuthority(List<RoleEntity> userRoles) {
    if (CollectionUtils.isEmpty(userRoles)) {
      return Collections.emptyList();
    }
    return userRoles.stream()
        .map(role -> new SimpleGrantedAuthority(role.getName()))
        .collect(Collectors.toList());
  }
}
