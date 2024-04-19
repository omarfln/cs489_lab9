package org.example.project.controller;
import org.example.project.dto.UserDTO;
import org.example.project.model.User;
import org.example.project.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mbweb/api/v1/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserDTO>> listAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.addUser(user));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.removeUserById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search/{searchString}")
    public ResponseEntity<List<UserDTO>> searchUsers(@PathVariable String searchString) {
        return ResponseEntity.ok(userService.searchUser(searchString));
    }
}
