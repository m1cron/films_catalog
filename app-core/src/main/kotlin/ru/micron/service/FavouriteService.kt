package ru.micron.service

import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import ru.micron.dto.FavouriteDto
import ru.micron.dto.PageableData
import ru.micron.dto.PageableResponse
import ru.micron.dto.omdb.FilmResponseDto
import ru.micron.mapper.FilmMapper
import ru.micron.persistence.model.Film
import ru.micron.persistence.repository.FilmRepository
import ru.micron.persistence.repository.UserRepository
import java.util.*

@Service
class FavouriteService(
    private val userRepository: UserRepository,
    private val filmRepository: FilmRepository,
    private val userService: UserService,
    private val filmMapper: FilmMapper
) {
    fun getUserFilmsByUserUuid(
        uuid: UUID?, page: Int, size: Int
    ): PageableResponse<FilmResponseDto> {
        val user = userService.findById(uuid)
        val result = filmRepository.findFilmsByUsers(user, PageRequest.of(page - 1, size))
        val list = result.content.map { film: Film? -> filmMapper.toDto(film) }
        return PageableResponse(
            list,
            PageableData(
                result.totalElements,
                page.toLong(),
                size.toLong(),
                result.size.toLong()
            )
        )
    }

    fun checkIsFavourite(dto: FavouriteDto): Boolean {
        return filmRepository.existsFilmByUsersAndImdbID(
            userService.findById(dto.userId),
            dto.imdbId
        )
    }

    fun addFavourite(dto: FavouriteDto) {
        val film = filmRepository.getById(dto.imdbId)
        val user = userRepository.getById(dto.userId)
            .addToFavourite(film)
        userRepository.save(user)
    }

    fun removeFavourite(dto: FavouriteDto) {
        val film = filmRepository.getById(dto.imdbId)
        val user = userRepository.getById(dto.userId)
            .removeFromFavourite(film)
        userRepository.save(user)
    }
}