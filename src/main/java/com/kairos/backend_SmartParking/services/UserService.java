package com.kairos.backend_SmartParking.services;

import com.kairos.backend_SmartParking.entities.Users;
import com.kairos.backend_SmartParking.dto.CarResponse;
import com.kairos.backend_SmartParking.dto.UserResponse;
import com.kairos.backend_SmartParking.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ================= CREATE =================
    public Users create(Users user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        return userRepository.save(user);
    }

    // ================= READ ALL =================
    @Transactional(readOnly = true)
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    // ================= READ ONE =================
    @Transactional(readOnly = true)
    public Users findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // ================= READ USER + CARS =================
    @Transactional(readOnly = true)
    public UserResponse findUserWithCars(UUID id) {
        Users user = findById(id);

        List<CarResponse> cars = user.getCars()
                .stream()
                .map(car -> new CarResponse(car.getId(), car.getModele(), user.getUsername()))
                .toList();

        return new UserResponse(user.getId(), user.getUsername(), cars);
    }

    // ================= UPDATE =================
    public Users updateUser(UUID id, Users data) {
        Users user = findById(id);
        user.setUsername(data.getUsername());
        return userRepository.save(user);
    }

    // ================= DELETE =================
    public void delete(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }
}
