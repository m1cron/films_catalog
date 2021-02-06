package ru.micron.rest.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.micron.dto.UserDTO;
import ru.micron.exception.NoSuchEntityException;
import ru.micron.model.User;
import ru.micron.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminRestControllerV1 {

    private final UserService userService;

    @Autowired
    public AdminRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> allUsers() {
        return userService.getAll();
    }

    @GetMapping(value = "users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        UserDTO result = UserDTO.fromUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("users/")
    public User addUser(@RequestBody User user) {
        userService.register(user);
        return user;
    }

    // TODO edit User method

    @DeleteMapping("users/{id}")
    public String deleteUser(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) {
            throw new NoSuchEntityException(String.format("There is no user with ID = %d in Database", id));
        }
        userService.deleteById(id);
        return String.format("User with ID = %d was deleted", id);
    }

}
