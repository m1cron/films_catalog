package ru.micron.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

data class CustomUserDetails(
    var uuid: UUID,
    private val username: String,
    private val password: String,
    private val isEnabled: Boolean,
    private val authorities: MutableCollection<out GrantedAuthority>
) : UserDetails {
    override fun getUsername(): String = username
    override fun getPassword(): String = password
    override fun isEnabled(): Boolean = isEnabled
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = authorities
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
}