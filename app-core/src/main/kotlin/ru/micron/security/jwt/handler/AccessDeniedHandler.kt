package ru.micron.security.jwt.handler

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import java.io.IOException
import java.time.OffsetDateTime
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AccessDeniedHandler(
    private val objectMapper: ObjectMapper
) : AccessDeniedHandler {

    @Throws(IOException::class, ServletException::class)
    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.status = HttpServletResponse.SC_FORBIDDEN
        val body = mapOf<String, Any?>(
            "timestamp" to OffsetDateTime.now(),
            "status" to HttpServletResponse.SC_FORBIDDEN,
            "error" to HttpStatus.FORBIDDEN.reasonPhrase,
            "message" to accessDeniedException.message,
            "path" to request.servletPath
        )
        objectMapper.writeValue(response.outputStream, body)
    }
}