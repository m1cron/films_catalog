package ru.micron.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.micron.persistence.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findByName(String name);
}
