package ru.micron.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.micron.dto.UserDto;
import ru.micron.mapper.UserMapper;
import ru.micron.model.Role;
import ru.micron.model.Roles;
import ru.micron.model.Status;
import ru.micron.model.User;
import ru.micron.repository.RoleRepository;
import ru.micron.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserMapper userMapper;

  public UserDto register(User user) {
    Role roleUser = roleRepository.findByName(Roles.USER.name());
    List<Role> userRoles = new ArrayList<>();
    userRoles.add(roleUser);

    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRoles(userRoles);
    user.setStatus(Status.ACTIVE);

    User registerUser = userRepository.save(user);
    return userMapper.toDto(registerUser);
  }

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public User findByUsername(String username) {
    User result = userRepository.findUserByUsername(username);
    if (result == null) {
      throw new RuntimeException("no user found by username: " + username);
    }
    return result;
  }

  public User findById(Long id) {
    User result = userRepository.findById(id).orElse(null);
    if (result == null) {
      throw new RuntimeException("no user found by id: " + id);
    }
    return result;
  }

  public UserDto editUserInfo(UserDto userDto) {
    User user = userRepository.findUserByUsername(userDto.getUsername());
    if (!userDto.getUsername().isBlank())
      user.setUsername(userDto.getUsername());
    if (!userDto.getEmail().isBlank())
      user.setEmail(userDto.getEmail());
    if (!userDto.getFirstName().isBlank())
      user.setFirstName(userDto.getFirstName());
    if (!userDto.getLastName().isBlank())
      user.setLastName(userDto.getLastName());
    return userMapper.toDto(userRepository.save(user));
  }

  public void deleteById(Long id) {
    userRepository.deleteById(id);
  }
}
