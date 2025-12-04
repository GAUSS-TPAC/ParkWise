package com.kairos.backend_SmartParking.controllers;

import com.kairos.backend_SmartParking.entities.Users;
import com.kairos.backend_SmartParking.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public Users create(@RequestBody Users user) {
        return userService.create(user);
    }

    @GetMapping
    public List<Users> getAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Users getById(@PathVariable UUID id) {
        return userService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        userService.delete(id);
    }
}
