package ru.micron.rest

import org.slf4j.LoggerFactory
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.micron.dto.BasicResponse
import ru.micron.dto.FavouriteDto
import ru.micron.dto.PageableResponse
import ru.micron.dto.omdb.FilmResponseDto
import ru.micron.service.FavouriteService
import java.util.*
import javax.validation.constraints.Max
import javax.validation.constraints.Min

@CrossOrigin(origins = ["*"], maxAge = 3600)
@Validated
@RestController
@RequestMapping("/api/v1/favourite")
class FavouriteController(
    private val favouriteService: FavouriteService
) {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @GetMapping
    fun getFavouriteByUserUuid(
        @RequestParam uuid: UUID?,
        @RequestParam(defaultValue = "1") pageNum: @Min(1) Int,
        @RequestParam(defaultValue = "12") pageSize: @Min(1) @Max(500) Int
    ): BasicResponse<PageableResponse<FilmResponseDto>> {
        log.debug(
            "getFavouriteByUserUuid uuid:{} pageNum:{} pageSize:{}",
            uuid,
            pageNum,
            pageSize
        )
        return BasicResponse(favouriteService.getUserFilmsByUserUuid(uuid, pageNum, pageSize))
    }

    @PostMapping("/checkIsFavourite")
    fun checkIsFavourite(@RequestBody dto: FavouriteDto): BasicResponse<Boolean> {
        log.debug("checkIsFavourite {}", dto)
        return BasicResponse(favouriteService.checkIsFavourite(dto), true)
    }

    @PostMapping("/add")
    fun addFavourite(@RequestBody dto: FavouriteDto): BasicResponse<Void> {
        log.debug("addFavourite {}", dto)
        favouriteService.addFavourite(dto)
        return BasicResponse()
    }

    @PostMapping("/remove")
    fun removeFromFavourite(@RequestBody dto: FavouriteDto): BasicResponse<Void> {
        log.debug("removeFromFavourite {}", dto)
        favouriteService.removeFavourite(dto)
        return BasicResponse()
    }
}