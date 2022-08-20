package ru.micron.security

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import ru.micron.mapper.JwtUserMapper
import ru.micron.service.UserService

@Service
class UserDetailsService(
    private val userService: UserService,
    private val jwtUserMapper: JwtUserMapper
) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        return jwtUserMapper.toUserDetails(userService.findByUsername(username))
    }
}