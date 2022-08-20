package ru.micron.mapper

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.springframework.util.CollectionUtils
import ru.micron.dto.omdb.FilmResponseDto
import ru.micron.dto.omdb.RatingsDto
import ru.micron.persistence.model.Film
import ru.micron.persistence.model.FilmRating
import java.util.*

@Mapper(componentModel = "spring", imports = [UUID::class])
abstract class FilmMapper {

    @Mapping(target = "users", ignore = true)
    @Mapping(target = "ageRate", source = "dto.ageRate")
    @Mapping(target = "awards", source = "dto.awards")
    @Mapping(target = "ratings", expression = "java(toEntities(dto))")
    abstract fun toEntity(dto: FilmResponseDto?): Film

    @Mapping(target = "film", ignore = true)
    @Mapping(target = "uuid", expression = "java(UUID.randomUUID())")
    @Mapping(target = "imdbId", source = "filmDto.imdbID")
    @Mapping(target = "source", source = "dto.source")
    @Mapping(target = "value", source = "dto.value")
    abstract fun toEntity(dto: RatingsDto?, filmDto: FilmResponseDto?): FilmRating

    fun toEntities(film: FilmResponseDto): List<FilmRating> {
        val list: MutableList<RatingsDto>? = film.ratings
        return if (CollectionUtils.isEmpty(list)) {
            emptyList()
        } else list!!.map { o: RatingsDto? -> toEntity(o, film) }
    }

    @Mapping(target = "ratings", expression = "java(toDtoRatings(film))")
    abstract fun toDto(film: Film?): FilmResponseDto

    fun toDtoRatings(filmEntity: Film): List<RatingsDto> {
        val list = filmEntity.ratings
        return if (CollectionUtils.isEmpty(list)) {
            emptyList()
        } else list.map { entity: FilmRating? -> this.toDto(entity) }
    }

    abstract fun toDto(entity: FilmRating?): RatingsDto
}