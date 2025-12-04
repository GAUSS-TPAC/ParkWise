package com.kairos.backend_SmartParking.services;

import com.kairos.backend_SmartParking.entities.Users;
import com.kairos.backend_SmartParking.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users create(Users user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        return userRepository.save(user);
    }

    public List<Users> findAll() {
        return userRepository.findAll();
    }

    public Users findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void delete(UUID id) {
        userRepository.deleteById(id);
    }
}
