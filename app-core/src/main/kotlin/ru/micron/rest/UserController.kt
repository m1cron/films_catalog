package ru.micron.rest

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.micron.dto.BasicResponse
import ru.micron.dto.UserDto
import ru.micron.service.UserService
import java.util.*

@CrossOrigin(origins = ["*"], maxAge = 3600)
@Validated
@RestController
@RequestMapping("/api/v1/user")
class UserController(
    private val userService: UserService
) {
    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: UUID): UserDto {
        return userService.getUserById(id)
    }

    @PatchMapping
    fun editUser(@RequestBody user: UserDto): BasicResponse<Void> {
        userService.editUserData(user)
        return BasicResponse()
    }
}