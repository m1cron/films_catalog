package ru.micron.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import ru.micron.security.jwt.JwtTokenFilter
import ru.micron.security.jwt.handler.AccessDeniedHandler
import ru.micron.security.jwt.handler.AuthEntryPointJwt

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig(
    private val userDetailsService: UserDetailsService,
    private val jwtTokenFilter: JwtTokenFilter,
    private val passwordEncoder: PasswordEncoder,
    private val authEntryPointJwt: AuthEntryPointJwt,
    private val accessDeniedHandler: AccessDeniedHandler
) : WebSecurityConfigurerAdapter() {

    companion object {
        val AUTH_WHITELIST = arrayOf(
            "/api/v1/auth/**",
            "/api/v1/film/*",
            "/swagger-ui/**",
            "/swagger-ui**",
            "/v3/api-docs/**",
            "/actuator/**",
            "/favicon.ico"
        )
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.cors()
            .and()
            .csrf().disable()
            .exceptionHandling().authenticationEntryPoint(authEntryPointJwt)
            .accessDeniedHandler(accessDeniedHandler)
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers(*AUTH_WHITELIST).permitAll()
            .antMatchers("/api/v1/admin/**").hasRole("ADMIN")
            .anyRequest().authenticated()
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter::class.java)
    }

    @Throws(Exception::class)
    public override fun configure(authenticationManagerBuilder: AuthenticationManagerBuilder) {
        authenticationManagerBuilder
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder)
    }

    @Bean
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }
}