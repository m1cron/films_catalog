package ru.micron.rest.handler

import com.fasterxml.jackson.core.JsonParseException
import org.apache.tomcat.util.ExceptionUtils
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.validation.BindException
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.server.ResponseStatusException
import ru.micron.dto.BasicResponse
import ru.micron.dto.ErrorObject
import java.nio.file.AccessDeniedException
import java.time.format.DateTimeParseException

@RestControllerAdvice
class ControllerExceptionHandler {

    private val log = LoggerFactory.getLogger(this.javaClass)

    @ExceptionHandler(Throwable::class)
    fun handleError(e: Throwable): ResponseEntity<BasicResponse<Any>> {
        val rootCause = ExceptionUtils.unwrapInvocationTargetException(e)
        var httpStatus = HttpStatus.INTERNAL_SERVER_ERROR
        var errorMes = rootCause.message
        when (rootCause) {
            is DateTimeParseException -> {
                errorMes =
                    (e as DateTimeParseException).parsedString + " is not valid datetime value"
            }
            is AccessDeniedException -> {
                httpStatus = HttpStatus.FORBIDDEN
            }
            is BadCredentialsException, is AuthenticationServiceException -> {
                httpStatus = HttpStatus.BAD_REQUEST
            }
            is JsonParseException -> {
                httpStatus = HttpStatus.BAD_REQUEST
                errorMes = "HTTP request body is not a valid JSON: " + e.message
            }
            is BindException -> {
                httpStatus = HttpStatus.BAD_REQUEST
                errorMes = (e as BindException)
                    .bindingResult.allErrors
                    .joinToString(", ") { error: ObjectError ->
                        getMessageFromFieldError(
                            error
                        )
                    }
            }
            is ResponseStatusException -> {
                httpStatus = HttpStatus.BAD_REQUEST
            }
        }
        val errorObject = ErrorObject("" + httpStatus.value(), errorMes)
        log.error(errorObject.toString(), rootCause)
        return ResponseEntity.status(httpStatus.value())
            .body(buildErrorResponse(errorObject))
    }

    private fun getMessageFromFieldError(error: ObjectError): String {
        val field = (error as FieldError).field
        return "$field ${error.defaultMessage}"
    }

    private fun buildErrorResponse(errorObject: ErrorObject): BasicResponse<Any> {
        return BasicResponse(data = null, success = false, error = errorObject)
    }
}