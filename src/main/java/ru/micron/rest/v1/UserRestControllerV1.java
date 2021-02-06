package ru.micron.rest.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.micron.exception.NoSuchEntityException;
import ru.micron.model.User;
import ru.micron.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestControllerV1 {

    private final UserService userService;

    @Autowired
    public UserRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> allUsers() {
        return userService.getAll();
    }

    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) {
            throw new NoSuchEntityException(String.format("There is no user with ID = %d in Database", id));
        }
        return user;
    }

    @PostMapping()
    public User addUser(@RequestBody User user) {
        userService.register(user);
        return user;
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
