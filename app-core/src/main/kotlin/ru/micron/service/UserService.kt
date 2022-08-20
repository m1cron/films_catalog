package ru.micron.service

import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import ru.micron.dto.UserDto
import ru.micron.mapper.UserMapper
import ru.micron.persistence.model.User
import ru.micron.persistence.repository.UserRepository
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
) {
    private val log = LoggerFactory.getLogger(this.javaClass)

    fun findByUsername(username: String): User {
        return userRepository.findUserByUsername(username)
            .orElseThrow { UsernameNotFoundException("No user found by username: $username") }
    }

    fun existsByUsername(name: String?): Boolean {
        return userRepository.existsUserByUsername(name!!)
    }

    fun register(userDto: UserDto?) {
        val (_, _, username) = userRepository.save(
            userMapper.register(userDto)
        )
        log.info("User with username {} was registered", username)
    }

    fun findAll(): List<User> {
        return userRepository.findAll()
    }

    fun findById(id: UUID?): User {
        if (id == null) {
            throw RuntimeException("Id is")
        }
        return userRepository.findById(id)
            .orElseThrow { UsernameNotFoundException("No user found by id: $id") }
    }

    fun getUserById(uuid: UUID): UserDto {
        return userMapper.toDto(findById(uuid))
    }

    fun editUserData(dto: UserDto) {
        val user = findById(dto.uuid)
        userRepository.save(userMapper.edit(user, dto))
    }

    fun deleteById(id: UUID) {
        val entity = userRepository.findById(id)
            .orElseThrow { UsernameNotFoundException("No user found by id: $id") }
        userRepository.delete(entity)
    }
}