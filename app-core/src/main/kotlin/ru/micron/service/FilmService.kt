package ru.micron.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import ru.micron.persistence.model.Film
import ru.micron.persistence.repository.FilmRepository

@Service
class FilmService(
    private val filmRepository: FilmRepository
) {
    fun findById(id: String): Film {
        return filmRepository.findById(id).orElseThrow {
            RuntimeException("There is no film with ID = $id in Database")
        }
    }

    fun findAllByPageableAnd(page: Int, size: Int): Page<Film> {
        val pageable: Pageable = PageRequest.of(page - 1, size)
        return filmRepository.findAll(pageable)
    }

    fun save(film: Film) {
        filmRepository.save(film)
    }

    fun deleteById(id: String) {
        val entity = findById(id)
        filmRepository.delete(entity)
    }
}