package ru.micron.rest.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.micron.dto.UserDTO;
import ru.micron.exception.NoSuchEntityException;
import ru.micron.mapper.UserMapper;
import ru.micron.model.User;
import ru.micron.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestControllerV1 {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping()
    public List<UserDTO> allUsers() {
        return userService.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) {
            throw new NoSuchEntityException(String.format("There is no user with ID = %d in Database", id));
        }
        return userMapper.toDto(user);
    }

    @PostMapping()
    public User addUser(@RequestBody User user) {
        return userService.register(user);
    }

    // TODO edit User method

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) {
            throw new NoSuchEntityException(String.format("There is no user with ID = %d in Database", id));
        }
        userService.deleteById(id);
        return String.format("User with ID = %d was deleted", id);
    }

}
