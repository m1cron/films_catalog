package ru.micron.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.micron.dto.ActorDto;
import ru.micron.model.Actor;

@Mapper(
    uses = RoleMapper.class,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
public abstract class ActorMapper {

  public static final ActorMapper INSTANCE = Mappers.getMapper(ActorMapper.class);

  public abstract ActorDto toDto(Actor actor);

}
