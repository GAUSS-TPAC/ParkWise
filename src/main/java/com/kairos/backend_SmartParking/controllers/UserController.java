package com.kairos.backend_SmartParking.controllers;

import com.kairos.backend_SmartParking.entities.Users;
import com.kairos.backend_SmartParking.services.UserService;
import com.kairos.backend_SmartParking.dto.UserResponse;
import org.springframework.http.ResponseEntity;
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

    // ================= CREATE =================
    @PostMapping
    public ResponseEntity<Users> create(@RequestBody Users user) {
        return ResponseEntity.ok(userService.create(user));
    }

    // ================= READ ALL =================
    @GetMapping
    public ResponseEntity<List<Users>> getAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    // ================= READ ONE =================
    @GetMapping("/{id}")
    public ResponseEntity<Users> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    // ================= READ USER + CARS =================
    @GetMapping("/{id}/cars")
    public ResponseEntity<UserResponse> getUserWithCars(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.findUserWithCars(id));
    }

    // ================= UPDATE =================
    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(
            @PathVariable UUID id,
            @RequestBody Users user
    ) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    // ================= DELETE =================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
