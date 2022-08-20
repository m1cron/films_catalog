package ru.micron.mapper

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import ru.micron.persistence.model.RoleEntity
import ru.micron.persistence.model.RoleEntity.ERole
import java.nio.charset.StandardCharsets
import java.util.*

@Mapper(componentModel = "spring", imports = [UUID::class, StandardCharsets::class])
interface RoleMapper {

    @Mapping(
        target = "id",
        expression = "java(UUID.nameUUIDFromBytes(name.name().getBytes(StandardCharsets.UTF_8)))"
    )
    @Mapping(target = "name", source = "name")
    @Mapping(target = "users", ignore = true)
    fun toEntity(name: ERole): RoleEntity
}