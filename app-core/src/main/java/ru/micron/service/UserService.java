package ru.micron.service;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.micron.dto.UserDto;
import ru.micron.mapper.UserMapper;
import ru.micron.persistence.model.User;
import ru.micron.persistence.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public User findByUsername(String username) {
    return userRepository.findUserByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("No user found by username: " + username));
  }

  public void register(UserDto userDto) {
    userRepository.save(userMapper.register(userDto));
  }

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public User findById(UUID id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new UsernameNotFoundException("No user found by id: " + id));
  }

  public UserDto getUserById(UUID uuid) {
    return userMapper.toDto(findById(uuid));
  }

  public void editUserData(UserDto dto) {
    User user = findByUsername(dto.getUsername());
    userRepository.save(userMapper.edit(user, dto));
  }

  public void deleteById(UUID id) {
    var entity = userRepository.findById(id)
        .orElseThrow(() -> new UsernameNotFoundException("No user found by id: " + id));
    userRepository.delete(entity);
  }
}
