package ru.micron.feign;

import org.springframework.cloud.openfeign.FeignClient;
import ru.micron.rest.v1.interfaces.ApiAuth;

@FeignClient(value = "app-films-catalog-core",
    url = "${app.feign.url.auth}")
public interface AuthFeignClient extends ApiAuth {
}
