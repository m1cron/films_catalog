package ru.micron.persistence.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.micron.persistence.model.Film
import ru.micron.persistence.model.User

@Repository
interface FilmRepository : JpaRepository<Film, String> {
    fun findAllByOrderByImdbRatingDesc(): List<Film>
    fun findAllByTitle(title: String?): List<Film>
    fun findAllByYear(year: String?): List<Film>
    fun findAllByYearLessThanEqual(year: String): List<Film>
    fun findAllByYearGreaterThanEqual(year: String): List<Film>
    fun findFilmsByUsers(user: User, pageable: Pageable): Page<Film>
    fun existsFilmByUsersAndImdbID(user: User, imdbID: String): Boolean
}