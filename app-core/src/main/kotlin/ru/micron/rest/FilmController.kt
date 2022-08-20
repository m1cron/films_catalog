package ru.micron.rest

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.micron.dto.BasicResponse
import ru.micron.dto.PageableData
import ru.micron.dto.PageableResponse
import ru.micron.dto.omdb.FilmResponseDto
import ru.micron.mapper.FilmMapper
import ru.micron.persistence.model.Film
import ru.micron.service.FilmService
import javax.validation.constraints.Max
import javax.validation.constraints.Min

@CrossOrigin(origins = ["*"], maxAge = 3600)
@Validated
@RestController
@RequestMapping("/api/v1/film")
class FilmController(
    private val filmService: FilmService,
    private val filmMapper: FilmMapper
) {
    @GetMapping("/getAll")
    fun getFilms(
        @RequestParam(defaultValue = "1") pageNum: @Min(1) Int,
        @RequestParam(defaultValue = "12") pageSize: @Min(1) @Max(500) Int
    ): BasicResponse<PageableResponse<FilmResponseDto>> {
        val result = filmService.findAllByPageableAnd(pageNum, pageSize)

        val list = result.content.map { film: Film -> filmMapper.toDto(film) }

        return BasicResponse(
            PageableResponse(
                list, PageableData(
                    result.totalElements,
                    pageNum.toLong(),
                    pageSize.toLong(),
                    result.size.toLong()
                )
            )
        )
    }

    @GetMapping("/")
    fun getFilmById(@RequestParam imdbId: String): BasicResponse<FilmResponseDto> {
        val film = filmService.findById(imdbId)
        return BasicResponse(filmMapper.toDto(film))
    }
}