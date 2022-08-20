package ru.micron.service

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.util.CollectionUtils
import ru.micron.feign.OMDbFeignClient
import ru.micron.mapper.FilmMapper
import ru.micron.persistence.model.FilmReceive
import ru.micron.persistence.repository.FilmReceiveRepository
import ru.micron.persistence.repository.FilmRepository
import java.util.function.Consumer
import javax.annotation.PostConstruct

@Service
class WarmupFilmService(
    private val feignClient: OMDbFeignClient,
    private val mapper: FilmMapper,
    private val filmRepository: FilmRepository,
    private val warmUpRepository: FilmReceiveRepository
) {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @Value("\${feign.OMDbApi.apiKey}")
    lateinit var apiKey: String

    @PostConstruct
    fun warmUp() {
        val filmsForReceived = warmUpRepository.findFilmReceivesByIsReceivedFalse()

        if (CollectionUtils.isEmpty(filmsForReceived)) {
            log.info("All films was received")
            return
        }
        filmsForReceived.forEach(
            Consumer { o: FilmReceive ->
                val imdbId = o.imdbId
                val receivedMovieDto = feignClient.receiveFilms(imdbId, null, null, apiKey)
                val entity = mapper.toEntity(receivedMovieDto)
                filmRepository.save(entity)
                o.isReceived = true
                warmUpRepository.save(o)
                log.info("Film with imdbId: {} was received", imdbId)
            })
    }
}