package ru.micron.feign;

import org.springframework.cloud.openfeign.FeignClient;
import ru.micron.v1.ApiAuth;

@FeignClient(value = "app-films-catalog-core",
    url = "${app.feign.url.auth}")
public interface AuthFeignClient extends ApiAuth {
}
