package ru.micron.feign;

import org.springframework.cloud.openfeign.FeignClient;
import ru.micron.rest.v1.interfaces.ApiFilm;

@FeignClient(value = "app-films-catalog-core",
    url = "${app.feign.url.films}")
public interface FilmsCatalogFeignClient extends ApiFilm {
}

