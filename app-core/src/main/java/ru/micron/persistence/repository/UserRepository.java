package ru.micron.persistence.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.micron.persistence.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

  User findUserByUsername(String username);
}
