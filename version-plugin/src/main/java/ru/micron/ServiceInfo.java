package ru.micron;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ServiceInfo implements Serializable {

  private String serviceName;
  private String version;
  private String buildTime = LocalDateTime.now()
      .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
  private String branchName;
  private String commitHash;
  private String commitAuthor;
  private String commitMessage;
}
