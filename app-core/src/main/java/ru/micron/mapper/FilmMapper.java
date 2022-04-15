package ru.micron.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.micron.dto.FilmDto;
import ru.micron.model.Film;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public abstract class FilmMapper {

  public static final FilmMapper INSTANCE = Mappers.getMapper(FilmMapper.class);

  public abstract FilmDto toDto(Film film);

  public abstract Film toEntity(FilmDto filmDTO);

}
