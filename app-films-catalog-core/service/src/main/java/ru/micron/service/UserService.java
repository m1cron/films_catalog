package ru.micron.service;

import ru.micron.dto.UserDto;
import ru.micron.model.User;

import java.util.List;

public interface UserService {

  UserDto register(User user);

  List<User> findAll();

  User findByUsername(String username);

  User findById(Long id);

  void deleteById(Long id);

  UserDto editUserInfo(UserDto userDto);
}
