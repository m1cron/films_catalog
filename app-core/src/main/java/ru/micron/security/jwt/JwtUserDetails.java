package ru.micron.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@AllArgsConstructor
public class JwtUserDetails implements UserDetails {

  private final String username;
  private final String password;
  private final boolean isEnabled;
  private final Collection<? extends GrantedAuthority> authorities;

  @JsonIgnore
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }
}
