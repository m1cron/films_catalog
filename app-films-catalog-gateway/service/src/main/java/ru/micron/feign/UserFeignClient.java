package ru.micron.feign;

import org.springframework.cloud.openfeign.FeignClient;
import ru.micron.rest.v1.interfaces.ApiUser;

@FeignClient(value = "app-films-catalog-core",
    url = "${app.feign.url.user}")
public interface UserFeignClient extends ApiUser {

}
