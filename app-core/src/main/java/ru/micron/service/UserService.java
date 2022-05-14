package ru.micron.service;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.micron.config.SecurityConfig.Role;
import ru.micron.dto.RegisterUserDto;
import ru.micron.mapper.UserMapper;
import ru.micron.persistence.model.RoleEntity;
import ru.micron.persistence.model.Status;
import ru.micron.persistence.model.User;
import ru.micron.persistence.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;
  private final UserMapper userMapper;

  public void register(RegisterUserDto registerUserDto) {
    var user = userMapper.toEntity(registerUserDto);

    user.setUuid(UUID.randomUUID());
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRoles(List.of(new RoleEntity(Role.USER)));
    user.setStatus(Status.ACTIVE);
    userRepository.save(user);
  }

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public User findByUsername(String username) {
    User result = userRepository.findUserByUsername(username);
    if (result == null) {
      throw new UsernameNotFoundException("No user found by username: " + username);
    }
    return result;
  }

  public User findById(UUID id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new UsernameNotFoundException("No user found by id: " + id));
  }

  public void editUserInfo(RegisterUserDto userDto) {
    User user = userRepository.findUserByUsername(userDto.getUsername());
    if (!userDto.getUsername().isBlank()) {
      user.setUsername(userDto.getUsername());
    }
    if (!userDto.getEmail().isBlank()) {
      user.setEmail(userDto.getEmail());
    }
    if (!userDto.getFirstName().isBlank()) {
      user.setFirstName(userDto.getFirstName());
    }
    if (!userDto.getLastName().isBlank()) {
      user.setLastName(userDto.getLastName());
    }
    userRepository.save(user);
  }

  public void deleteById(UUID id) {
    var entity = userRepository.findById(id)
        .orElseThrow(() -> new UsernameNotFoundException("No user found by id: " + id));
    userRepository.delete(entity);
  }
}
