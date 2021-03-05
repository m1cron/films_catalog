package ru.micron.feign;

import org.springframework.cloud.openfeign.FeignClient;
import ru.micron.rest.v1.interfaces.ApiAdmin;

@FeignClient(value = "app-films-catalog-core",
    url = "${app.feign.url.admin}")
public interface AdminFeignClient extends ApiAdmin {

}
