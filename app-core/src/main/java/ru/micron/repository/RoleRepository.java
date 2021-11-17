package ru.micron.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.micron.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findByName(String name);
}
