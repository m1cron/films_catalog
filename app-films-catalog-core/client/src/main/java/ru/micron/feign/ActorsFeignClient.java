package ru.micron.feign;

import org.springframework.cloud.openfeign.FeignClient;
import ru.micron.v1.ApiActors;

@FeignClient(value = "app-films-catalog-core",
    url = "${app.feign.url.actors}")
public interface ActorsFeignClient extends ApiActors {
}
