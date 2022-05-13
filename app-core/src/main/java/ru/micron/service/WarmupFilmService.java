package ru.micron.service;

import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ru.micron.feign.OMDbFeignClient;
import ru.micron.mapper.FilmMapper;
import ru.micron.persistence.repository.FilmReceiveRepository;
import ru.micron.persistence.repository.FilmRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class WarmupFilmService {

  private final OMDbFeignClient feignClient;
  private final FilmMapper mapper;
  private final FilmRepository filmRepository;
  private final FilmReceiveRepository warmUpRepository;

  @Value("${feign.OMDbApi.apiKey}")
  private String apiKey;

  @PostConstruct
  public void warmUp() {
    var filmsForReceived = warmUpRepository.findFilmReceivesByIsReceivedFalse();

    if (CollectionUtils.isEmpty(filmsForReceived)) {
      log.info("All films was received");
      return;
    }

    filmsForReceived.forEach(
        o -> {
          var imdbId = o.getImdbId();
          var receivedMovieDto = feignClient.receiveFilms(imdbId, null, null, apiKey);
          var entity = mapper.toEntity(receivedMovieDto);
          filmRepository.save(entity);

          o.setIsReceived(true);
          warmUpRepository.save(o);
          log.info("Film with imdbId: {} was received", imdbId);
        });
  }
}
