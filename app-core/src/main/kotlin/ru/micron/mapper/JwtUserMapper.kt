package ru.micron.mapper

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import ru.micron.persistence.model.RoleEntity
import ru.micron.persistence.model.User
import ru.micron.security.CustomUserDetails

@Mapper(componentModel = "spring")
abstract class JwtUserMapper {

    @Mapping(target = "authorities", expression = "java(mapToGrantedAuthority(user.getRoles()))")
    @Mapping(target = "isEnabled", expression = "java(User.Status.ACTIVE.equals(user.getStatus()))")
    abstract fun toUserDetails(user: User): CustomUserDetails

    protected fun mapToGrantedAuthority(userRoles: MutableSet<RoleEntity>): List<GrantedAuthority> {
        return userRoles
            .map { (_, name): RoleEntity ->
                SimpleGrantedAuthority(
                    name!!.name
                )
            }
    }
}