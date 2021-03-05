package ru.micron.service;

import ru.micron.model.User;

import java.util.List;

public interface UserService {

  User register(User user);

  List<User> findAll();

  User findByUsername(String username);

  User findById(Long id);

  void deleteById(Long id);
}
