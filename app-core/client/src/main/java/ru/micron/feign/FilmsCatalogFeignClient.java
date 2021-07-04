package ru.micron.feign;

import org.springframework.cloud.openfeign.FeignClient;
import ru.micron.v1.ApiFilm;

@FeignClient(value = "app-films-catalog-core",
    url = "${app.feign.url.films}")
public interface FilmsCatalogFeignClient extends ApiFilm {
}

