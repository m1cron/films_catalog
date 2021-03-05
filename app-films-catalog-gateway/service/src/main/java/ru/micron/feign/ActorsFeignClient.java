package ru.micron.feign;

import org.springframework.cloud.openfeign.FeignClient;
import ru.micron.rest.v1.interfaces.ApiActors;

@FeignClient(value = "app-films-catalog-core",
    url = "${app.feign.url.actors}")
public interface ActorsFeignClient extends ApiActors {

}
