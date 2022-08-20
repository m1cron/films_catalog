package ru.micron.feign

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.micron.dto.omdb.ApiSearchResponse
import ru.micron.dto.omdb.FilmResponseDto
import ru.micron.dto.omdb.FilmType

@FeignClient(name = "filmFeignClient", url = "\${feign.OMDbApi.url}")
interface OMDbFeignClient {
    @GetMapping("/")
    fun receiveFilms(
        @RequestParam("i") id: String?,
        @RequestParam("type") type: FilmType?,
        @RequestParam("y") year: String?,
        @RequestParam("apikey") apiKey: String?
    ): FilmResponseDto?

    @GetMapping("/")
    fun receiveFilmByTitle(
        @RequestParam("s") title: String?,
        @RequestParam("type") type: FilmType?,
        @RequestParam("y") year: String?,
        @RequestParam("page") page: Int?,
        @RequestParam("apikey") apiKey: String?
    ): ApiSearchResponse?
}