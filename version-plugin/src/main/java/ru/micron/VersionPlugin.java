package ru.micron;

import static org.gradle.api.Project.PATH_SEPARATOR;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

@Slf4j
public class VersionPlugin implements Plugin<Project> {

  private static final String EMPTY = "";

  private static final String MAJOR = "[MAJOR]";
  private static final String MINOR = "[MINOR]";
  private static final String BUGFIX = "[BUGFIX]";

  private static final int COMMIT_HASH_INDEX = 0;
  private static final int COMMIT_AUTHOR_INDEX = 1;
  private static final int BRANCH_NAME_INDEX = 2;
  private static final int COMMIT_MESSAGE_INDEX = 3;

  private static final String LINE_SEPARATORS_REGEX = "\\r?\\n";

  private static final TimeUnit EXEC_TIMEOUT_UNIT = TimeUnit.SECONDS;
  private static final int EXEC_TIMEOUT = 10;

  private static final long CURRENT_TIME_MILLIS = System.currentTimeMillis();

  private VersionPluginExtension pluginExtension;

  @Override
  public void apply(Project project) {
    this.pluginExtension =
        project.getExtensions().create("versionPlugin", VersionPluginExtension.class);
    pluginEntryPoint(project);
  }

  private void pluginEntryPoint(Project project) {
    try {
      project.setVersion(
          pluginExtension.isGenerateVersion()
              ? calculateVersionByCommits()
              : "1.0"
      );
      var lastCommit = getLastCommit();
      var serviceInfo = new ServiceInfo()
          .setServiceName(pluginExtension.getProjectName())
          .setVersion(project.getVersion().toString())
          .setCommitHash(lastCommit[COMMIT_HASH_INDEX])
          .setCommitAuthor(lastCommit[COMMIT_AUTHOR_INDEX])
          .setBranchName(lastCommit[BRANCH_NAME_INDEX])
          .setCommitMessage(lastCommit[COMMIT_MESSAGE_INDEX]);

      log.info("{}", serviceInfo);
      System.out.println("version " + project.getVersion());
      saveInfoFile(serviceInfo, project);
    } catch (Exception e) {
      log.warn(e.getMessage());
    }
  }

  @SneakyThrows
  private void saveInfoFile(ServiceInfo serviceInfo, Project project) {
    var path = Path.of(project.getProjectDir().getAbsolutePath()
        + "/src/main/resources/info.file").normalize();
    Files.write(path, getFieldsFromObject(serviceInfo).getBytes());
  }

  @SneakyThrows
  private String getFieldsFromObject(ServiceInfo serviceInfo) {
    var builder = new StringBuilder();
    var fields = ServiceInfo.class.getDeclaredFields();
    var src = serviceInfo.getClass().getDeclaredFields();

    for (var i = 0; i < fields.length; i++) {
      src[i].setAccessible(true);
      builder
          .append(fields[i].getName())
          .append("$$")
          .append(src[i].get(serviceInfo))
          .append('\n');
    }
    return builder.toString();
  }

  @SneakyThrows
  private String[] getLastCommit() {
    var process = Runtime.getRuntime()
        .exec(pluginExtension.getLastCommitGitCommand());
    var commitInfo = readString(process.getInputStream())
        .replace("\"", EMPTY)
        .split(PATH_SEPARATOR);
    process.waitFor(EXEC_TIMEOUT, EXEC_TIMEOUT_UNIT);
    return commitInfo;
  }

  @SneakyThrows
  private String[] getCommitHistory() {
    var process = Runtime.getRuntime()
        .exec(pluginExtension.getCommitHistoryGitCommand());
    var splittedHistory = readString(process.getInputStream())
        .split(LINE_SEPARATORS_REGEX);
    process.waitFor(EXEC_TIMEOUT, EXEC_TIMEOUT_UNIT);
    return splittedHistory;
  }

  private String calculateVersionByCommits() {
    var projectName = "#" + pluginExtension.getProjectName();
    var commits = getCommitHistory();
    int major = 0;
    int minor = 0;
    int bugfix = 0;

    for (int i = commits.length - 1; i >= 0; i--) {
      int finalI = i;
      if ((pluginExtension.isByProjectName() && !commits[i].contains(projectName))
          || pluginExtension.getSkipMessages().stream()
          .anyMatch(s -> commits[finalI].contains(s))
      ) {
        continue;
      }

      if (commits[i].contains(MAJOR)) {
        major++;
        minor = 0;
        bugfix = 0;
      } else if (commits[i].contains(MINOR)) {
        minor++;
        bugfix = 0;
      } else if (commits[i].contains(BUGFIX)) {
        bugfix++;
      }
    }
    return String.format("%s.%s.%s%s", major, minor, bugfix,
        pluginExtension.isGenerateTimestamp()
            ? String.format("-%d", CURRENT_TIME_MILLIS)
            : EMPTY);
  }

  @SneakyThrows
  private String readString(InputStream inputStream) {
    int bufferSize = 1024;
    char[] buffer = new char[bufferSize];
    var out = new StringBuilder();
    var in = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
    for (int numRead; (numRead = in.read(buffer, 0, buffer.length)) > 0;) {
      out.append(buffer, 0, numRead);
    }
    return out.toString();
  }
}
