package ru.micron.controller.handler;

import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.MimeType;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.micron.dto.BasicResponse;
import ru.micron.dto.ErrorObject;

@Slf4j
@NoArgsConstructor
@RestControllerAdvice
public class ControllerExceptionHandler {

  private static final String COMMA = ",";
  private static final DateTimeFormatter DEFAULT_ERROR_TIME_PATTERN =
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  private static final String DEFAULT_ERROR_START_MESSAGE =
      "Извините, произошла непредвиденная ошибка.\n\n";
  private static final String DEFAULT_ERROR_END_MESSAGE =
      "Пожалуйста, отправьте этот текст в техническую поддержку.";
  private static final String DEFAULT_ERROR_MESSAGE_PATTERN =
      DEFAULT_ERROR_START_MESSAGE
          + "В %s в сервисе %s причина : %s\n"
          + DEFAULT_ERROR_END_MESSAGE;
  private static final String DATABASE_ERROR_MESSAGE_PATTERN =
      DEFAULT_ERROR_START_MESSAGE
          + "В %s в сервисе %s в базе данных причина : %s\n"
          + DEFAULT_ERROR_END_MESSAGE;

  @Value("${spring.application.name}")
  private String appName;

  @ExceptionHandler(Exception.class)
  public BasicResponse<Object> processException(Exception e) {
    var errorObject = new ErrorObject();
    var errorTime = LocalDateTime.now().format(DEFAULT_ERROR_TIME_PATTERN);
    var rootCause = ExceptionUtils.unwrapInvocationTargetException(e);
    if (rootCause instanceof SQLException || rootCause instanceof PersistenceException) {
      errorObject.setMessage(String.format(DATABASE_ERROR_MESSAGE_PATTERN,
          errorTime, appName, rootCause.getMessage()
      ));
    } else if (rootCause instanceof HttpMessageNotReadableException) {
      errorObject.setMessage(String.format(DEFAULT_ERROR_MESSAGE_PATTERN,
          errorTime, appName, String.format("Неправильное тело запроса: [%s]", rootCause.getMessage())
      ));
    } else if (rootCause instanceof AccessDeniedException) {
      errorObject.setMessage(String.format(DEFAULT_ERROR_MESSAGE_PATTERN,
          errorTime, appName, "Недостаточно прав для текущей роли"
      ));
    } else if (rootCause instanceof MethodArgumentNotValidException) {
      var castEx = (MethodArgumentNotValidException) rootCause;
      var message = new StringBuilder();
      for (FieldError item : castEx.getBindingResult().getFieldErrors()) {
        message.append(String.format("Неверное значение в поле [%s]\n", item.getRejectedValue()));
      }
      errorObject.setMessage(String.format(DEFAULT_ERROR_MESSAGE_PATTERN,
          errorTime, appName, message
      ));
    } else if (rootCause instanceof MissingServletRequestParameterException) {
      var castEx = (MissingServletRequestParameterException) rootCause;
      errorObject.setMessage(String.format(DEFAULT_ERROR_MESSAGE_PATTERN,
          errorTime, appName, String.format("Пропущен параметр [%s] с типом значения [%s]",
              castEx.getParameterName(), castEx.getParameterType())
      ));
    } else if (rootCause instanceof HttpRequestMethodNotSupportedException) {
      var castEx = (HttpRequestMethodNotSupportedException) rootCause;
      errorObject.setMessage(String.format(DEFAULT_ERROR_MESSAGE_PATTERN,
          errorTime, appName,
          String.format("[%s] тип не поддерживается для этого запроса. Поддерживаемые типы: [%s]",
              castEx.getMethod(), String.join(COMMA,
                  Optional.ofNullable(castEx.getSupportedMethods()).orElse(new String[0])))
      ));
    } else if (rootCause instanceof HttpMediaTypeNotSupportedException) {
      var castEx = (HttpMediaTypeNotSupportedException) rootCause;
      errorObject.setMessage(String.format(DEFAULT_ERROR_MESSAGE_PATTERN,
          errorTime, appName,
          String.format("[%s] тип не поддерживается для этого запроса. Поддерживаемые типы: [%s]",
              castEx.getContentType(), castEx.getSupportedMediaTypes().stream()
                  .map(MimeType::toString).collect(Collectors.joining(COMMA)))
      ));
    } else if (rootCause instanceof ConstraintViolationException) {
      errorObject.setMessage(String.format(DEFAULT_ERROR_MESSAGE_PATTERN,
          errorTime, appName, String.format("Неверное значение в поле [%s]", rootCause.getMessage())
      ));
    } else {
      errorObject.setMessage(String.format(DEFAULT_ERROR_MESSAGE_PATTERN,
          errorTime, appName, e.getMessage()
      ));
    }
    log.error(errorObject.toString(), e);
    return buildErrorResponse(errorObject);
  }

  private BasicResponse<Object> buildErrorResponse(ErrorObject errorObject) {
    return BasicResponse.builder()
        .error(errorObject)
        .success(false)
        .build();
  }
}
