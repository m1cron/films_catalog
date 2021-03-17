package ru.micron.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.micron.dto.FilmDto;
import ru.micron.model.Film;

@Mapper(
    uses = RoleMapper.class,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
public abstract class FilmMapper {

  public static final FilmMapper INSTANCE = Mappers.getMapper(FilmMapper.class);

  public abstract FilmDto toDto(Film film);

  public abstract Film toEntity(FilmDto filmDTO);

}
