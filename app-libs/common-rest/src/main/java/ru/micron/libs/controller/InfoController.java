package ru.micron.libs.controller;

import static net.logstash.logback.encoder.org.apache.commons.lang3.StringUtils.LF;

import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.micron.libs.dto.BasicResponse;
import ru.micron.libs.dto.ServiceInfo;

@RestController
public class InfoController {

  public static final String INFO_CONTROLLER_URL = "/info";

  private final ServiceInfo serviceInfo;

  public InfoController(@Value("classpath:info.file") Resource info)
      throws IOException, IllegalAccessException {
    var src = IOUtils.toString(info.getInputStream()).split(LF);

    serviceInfo = new ServiceInfo();
    var dest = serviceInfo.getClass().getDeclaredFields();

    for (var i = 0; i < dest.length; i++) {
      dest[i].setAccessible(true);
      dest[i].set(serviceInfo, src[i].split("\\$\\$")[1]);
    }
  }

  @GetMapping(INFO_CONTROLLER_URL)
  public BasicResponse<ServiceInfo> getInfo() {
    return new BasicResponse<ServiceInfo>()
        .setData(serviceInfo);
  }
}
