package ru.micron.dto

import java.util.*

data class JwtResponse(
    var uuid: UUID? = null,
    var token: String? = null,
    var username: String? = null,
    var roles: List<String>? = null
) {
    var type: String? = "Bearer"
}