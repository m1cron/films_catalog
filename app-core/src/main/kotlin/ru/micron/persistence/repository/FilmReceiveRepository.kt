package ru.micron.persistence.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.micron.persistence.model.FilmReceive

@Repository
interface FilmReceiveRepository : JpaRepository<FilmReceive, String> {
    fun findFilmReceivesByIsReceivedFalse(): List<FilmReceive>
}