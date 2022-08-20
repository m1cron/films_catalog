package ru.micron.persistence.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.micron.persistence.model.RoleEntity
import java.util.*

@Repository
interface RoleRepository : JpaRepository<RoleEntity, UUID> {
    fun findByName(name: String): RoleEntity
}