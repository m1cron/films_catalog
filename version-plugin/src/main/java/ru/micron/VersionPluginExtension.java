package ru.micron;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class VersionPluginExtension {

  private static final String EXECUTE_PLATFORM =
      System.getProperty("os.name").toLowerCase().contains("windows")
          ? "wsl -e git log --pretty=format:"
          : "git log --pretty=format:";

  private String projectName = "no project name";
  private boolean byProjectName = false;
  private List<String> skipMessages = List.of("[BYPASS]", "Pull request");
  private boolean generateTimestamp = false;
  private boolean generateVersion = false;
  private String lastCommitGitCommand = EXECUTE_PLATFORM + "'%H:%an:%d:%s' -n 1";
  private String commitHistoryGitCommand = EXECUTE_PLATFORM + "'%s'";
}
