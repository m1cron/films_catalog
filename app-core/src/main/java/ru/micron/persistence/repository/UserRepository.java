package ru.micron.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.micron.persistence.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
  User findUserByUsername(String username);
}
