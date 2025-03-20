package dio.api.controllers;

import dio.api.models.User;
import dio.api.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> showOneUser(@PathVariable Long id) {
        var returnedUser = userService.findById(id);
        return ResponseEntity.ok(returnedUser);
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User userToCreate) {
        var createdUser = userService.create(userToCreate);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getId())
                .toUri();

        return ResponseEntity.created(location).body(createdUser);
    }
}
