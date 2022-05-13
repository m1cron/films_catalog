package ru.micron.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.micron.persistence.model.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

  RoleEntity findByName(String name);
}
