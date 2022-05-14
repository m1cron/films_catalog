package ru.micron.persistence.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.micron.persistence.model.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {

  RoleEntity findByName(String name);
}
