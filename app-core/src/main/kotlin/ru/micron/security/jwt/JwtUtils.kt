package ru.micron.security.jwt

import io.jsonwebtoken.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtils {

    private val log = LoggerFactory.getLogger(this.javaClass)

    @Value("\${jwt.secret}")
    lateinit var jwtSecret: String

    @Value("\${jwt.expirationMills}")
    lateinit var expirationMills: Number

    fun generateJwtToken(authentication: Authentication): String {
        val userPrincipal = authentication.principal as UserDetails
        return Jwts.builder()
            .setSubject(userPrincipal.username)
            .setIssuedAt(Date())
            .setExpiration(Date(Date().time + expirationMills.toLong()))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact()
    }

    fun getUserNameFromJwtToken(claimsJws: Jws<Claims>): String {
        return claimsJws.body.subject
    }

    fun validateJwtToken(authToken: String?): Jws<Claims>? {
        try {
            return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken)
        } catch (e: SignatureException) {
            log.error("Invalid JWT signature: {}", e.message)
        } catch (e: MalformedJwtException) {
            log.error("Invalid JWT token: {}", e.message)
        } catch (e: ExpiredJwtException) {
            log.error("JWT token is expired: {}", e.message)
        } catch (e: UnsupportedJwtException) {
            log.error("JWT token is unsupported: {}", e.message)
        } catch (e: IllegalArgumentException) {
            log.error("JWT claims string is empty: {}", e.message)
        }
        return null
    }
}