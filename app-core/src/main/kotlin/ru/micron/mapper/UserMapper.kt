package ru.micron.mapper

import io.micrometer.core.instrument.util.StringUtils
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import ru.micron.dto.UserDto
import ru.micron.persistence.model.RoleEntity
import ru.micron.persistence.model.User
import java.time.LocalDateTime
import java.util.*

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.FIELD,
    uses = [RoleMapper::class],
    imports = [UUID::class, MutableSet::class, RoleEntity::class, StringUtils::class, LocalDateTime::class]
)
abstract class UserMapper {

    @Autowired protected lateinit var roleMapper: RoleMapper
    @Autowired protected lateinit var encoder: PasswordEncoder

    @Mapping(target = "password", ignore = true)
    abstract fun toDto(user: User?): UserDto

    @Mapping(target = "created", expression = "java(LocalDateTime.now())")
    @Mapping(target = "removeFromFavourite", ignore = true)
    @Mapping(target = "favouriteFilms", ignore = true)
    @Mapping(target = "uuid", expression = "java(UUID.randomUUID())")
    @Mapping(
        target = "roles",
        expression = "java(Set.of(roleMapper.toEntity(RoleEntity.ERole.ROLE_USER)))"
    )
    @Mapping(target = "status", expression = "java(User.Status.ACTIVE)")
    @Mapping(target = "password", expression = "java(encoder.encode(user.getPassword()))")
    abstract fun register(user: UserDto?): User

    @Mapping(target = "removeFromFavourite", ignore = true)
    @Mapping(target = "uuid", source = "before.uuid")
    @Mapping(
        target = "email",
        expression = "java(StringUtils.isNotBlank(dto.getEmail()) ? dto.getEmail() : before.getEmail())"
    )
    @Mapping(
        target = "username",
        expression = "java(StringUtils.isNotBlank(dto.getUsername()) ? dto.getUsername() : before.getUsername())"
    )
    @Mapping(
        target = "password",
        expression = "java(StringUtils.isNotBlank(dto.getPassword()) ? encoder.encode(dto.getPassword()) : before.getPassword())"
    )
    @Mapping(
        target = "firstName",
        expression = "java(StringUtils.isNotBlank(dto.getFirstName()) ? dto.getFirstName() : before.getFirstName())"
    )
    @Mapping(
        target = "lastName",
        expression = "java(StringUtils.isNotBlank(dto.getLastName()) ? dto.getLastName() : before.getLastName())"
    )
    abstract fun edit(before: User?, dto: UserDto?): User
}