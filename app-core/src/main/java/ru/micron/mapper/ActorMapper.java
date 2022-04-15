package ru.micron.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.micron.dto.ActorDto;
import ru.micron.persistence.model.Actor;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public abstract class ActorMapper {

  public static final ActorMapper INSTANCE = Mappers.getMapper(ActorMapper.class);

  public abstract ActorDto toDto(Actor actor);
}
