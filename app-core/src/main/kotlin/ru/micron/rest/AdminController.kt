package ru.micron.rest

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.micron.dto.BasicResponse
import ru.micron.dto.omdb.FilmResponseDto
import ru.micron.mapper.FilmMapper
import ru.micron.persistence.model.User
import ru.micron.service.FilmService
import ru.micron.service.UserService
import java.util.*

@CrossOrigin(origins = ["*"], maxAge = 3600)
@Validated
@RestController
@RequestMapping("/api/v1/admin")
class AdminController(
    private val userService: UserService,
    private val filmService: FilmService,
    private val filmMapper: FilmMapper
) {
    @GetMapping("/user")
    fun allUsers(): BasicResponse<List<User>> {
        return BasicResponse(userService.findAll())
    }

    @GetMapping("/user/{id}")
    fun getUserById(@PathVariable id: UUID): BasicResponse<User> {
        return BasicResponse(userService.findById(id))
    }

    @DeleteMapping("/user/{id}")
    fun deleteUser(@PathVariable id: UUID): BasicResponse<Void> {
        userService.deleteById(id)
        return BasicResponse()
    }

    @DeleteMapping("/film/{imdbId}")
    fun deleteFilm(@PathVariable imdbId: String): BasicResponse<Void> {
        filmService.deleteById(imdbId)
        return BasicResponse()
    }

    @PostMapping("/film")
    fun addFilm(@RequestBody film: FilmResponseDto): BasicResponse<Void> {
        filmService.save(filmMapper.toEntity(film))
        return BasicResponse()
    }

    @PutMapping("/film")
    fun editFilm(@RequestBody film: FilmResponseDto?): BasicResponse<Void> {
        filmService.save(filmMapper.toEntity(film))
        return BasicResponse()
    }
}