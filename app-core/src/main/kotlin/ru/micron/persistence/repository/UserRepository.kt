package ru.micron.persistence.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.micron.persistence.model.User
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, UUID> {
    fun findUserByUsername(username: String): Optional<User>
    fun existsUserByUsername(username: String): Boolean
}