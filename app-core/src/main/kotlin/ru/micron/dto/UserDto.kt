package ru.micron.dto

import java.util.*

data class UserDto(
    var uuid: UUID? = null,
    var username: String? = null,
    var password: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var email: String? = null
)
