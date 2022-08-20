package ru.micron.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import ru.micron.security.UserDetailsService
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtTokenFilter(
    private val jwtUtils: JwtUtils,
    private val userDetailsService: UserDetailsService
) : OncePerRequestFilter() {

    private val log = LoggerFactory.getLogger(this.javaClass)

    @Value("\${jwt.header}")
    lateinit var authorizationHeader: String

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        setupAuthentication(request)
        filterChain.doFilter(request, response)
    }

    private fun setupAuthentication(request: HttpServletRequest) {
        try {
            val jwt = parseJwt(request) ?: return
            val claimsJws: Jws<Claims> = jwtUtils.validateJwtToken(jwt) ?: return
            val username = jwtUtils.getUserNameFromJwtToken(claimsJws)
            val userDetails = userDetailsService.loadUserByUsername(username)
            val authentication = UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.authorities
            )
            authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
            SecurityContextHolder.getContext().authentication = authentication

        } catch (e: Exception) {
            log.error("Cannot set user authentication", e)
        }
    }

    private fun parseJwt(request: HttpServletRequest): String? {
        val headerAuth = request.getHeader(authorizationHeader)
        return if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            headerAuth.substring(7)
        } else null
    }
}