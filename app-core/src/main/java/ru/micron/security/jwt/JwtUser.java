package ru.micron.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.UUID;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

public class JwtUser implements UserDetails {

  private final UUID id;
  private final String email;
  private final String username;
  private final String firstName;
  private final String lastName;
  private final String password;
  private final Boolean enabled;
  private final Collection<? extends GrantedAuthority> authorities;

  public JwtUser(
      UUID id,
      String username,
      String firstName,
      String lastName,
      String email,
      String password,
      Collection<? extends GrantedAuthority> authorities,
      boolean enabled) {
    this.id = id;
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.authorities = authorities;
    this.enabled = enabled;
  }

  @JsonIgnore
  public UUID getId() {
    return id;
  }

  @Override
  public String getUsername() {
    return username;
  }

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

  public String getFirstname() {
    return firstName;
  }

  public String getLastname() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  @JsonIgnore
  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }
}
