package ru.micron.mapper;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;
import ru.micron.dto.omdb.FilmResponseDto;
import ru.micron.dto.omdb.RatingsDto;
import ru.micron.persistence.model.Film;
import ru.micron.persistence.model.FilmRating;

@Mapper(componentModel = "spring", imports = {UUID.class})
public abstract class FilmMapper {

  public static final FilmMapper INSTANCE = Mappers.getMapper(FilmMapper.class);

  @Mapping(target = "users", ignore = true)
  @Mapping(target = "ageRate", source = "dto.ageRate")
  @Mapping(target = "awards", source = "dto.awards")
  @Mapping(target = "ratings", expression = "java(toEntities(dto))")
  public abstract Film toEntity(FilmResponseDto dto);

  @Mapping(target = "film", ignore = true)
  @Mapping(target = "uuid", expression = "java(UUID.randomUUID())")
  @Mapping(target = "imdbId", source = "filmDto.imdbID")
  @Mapping(target = "source", source = "dto.source")
  @Mapping(target = "value", source = "dto.value")
  public abstract FilmRating toEntity(RatingsDto dto, FilmResponseDto filmDto);

  public List<FilmRating> toEntities(FilmResponseDto film) {
    var list = film.getRatings();
    if (CollectionUtils.isEmpty(list)) {
      return Collections.emptyList();
    }
    return list.stream().map(o -> toEntity(o, film)).collect(Collectors.toList());
  }

  @Mapping(target = "ratings", expression = "java(toDtoRatings(film))")
  public abstract FilmResponseDto toDto(Film film);

  public List<RatingsDto> toDtoRatings(Film filmEntity) {
    var list = filmEntity.getRatings();
    if (CollectionUtils.isEmpty(list)) {
      return Collections.emptyList();
    }
    return list.stream().map(this::toDto).collect(Collectors.toList());
  }

  public abstract RatingsDto toDto(FilmRating entity);
}
