package ru.micron.rest

import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.micron.dto.BasicResponse
import ru.micron.dto.JwtResponse
import ru.micron.dto.UserDto
import ru.micron.security.CustomUserDetails
import ru.micron.security.jwt.JwtUtils
import ru.micron.service.UserService

@CrossOrigin(origins = ["*"], maxAge = 3600)
@Validated
@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val jwtUtils: JwtUtils,
    private val userService: UserService
) {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: UserDto): BasicResponse<JwtResponse> {
        return BasicResponse(authenticate(loginRequest))
    }

    @PostMapping("/register")
    fun registerUser(@RequestBody registerRequest: UserDto): BasicResponse<JwtResponse> {
        if (userService.existsByUsername(registerRequest.username)) {
            throw AuthenticationServiceException(
                "User with username '" + registerRequest.username + "' already exists"
            )
        }
        userService.register(registerRequest)
        log.info("Token was created for user with name {}", registerRequest.username)
        return BasicResponse(authenticate(registerRequest))
    }

    @PostMapping("/check")
    fun checkIsRegistered(@RequestBody check: UserDto): BasicResponse<Void> {
        return BasicResponse(userService.existsByUsername(check.username))
    }

    private fun authenticate(user: UserDto): JwtResponse {
        val authentication = authenticationManager
            .authenticate(UsernamePasswordAuthenticationToken(user.username, user.password))
        SecurityContextHolder.getContext().authentication = authentication
        val jwt = jwtUtils.generateJwtToken(authentication)
        val userDetails = authentication.principal as CustomUserDetails
        val roles = userDetails.authorities.map { obj: GrantedAuthority -> obj.authority }

        log.info("Token was created for user with name {}", user.username)
        return JwtResponse(userDetails.uuid, jwt, userDetails.username, roles)
    }
}