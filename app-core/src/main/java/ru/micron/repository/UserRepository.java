package ru.micron.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.micron.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
  User findUserByUsername(String username);
}
