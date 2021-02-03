package ru.micron.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.micron.model.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
}
