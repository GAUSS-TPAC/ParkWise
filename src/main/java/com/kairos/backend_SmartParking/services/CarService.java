package com.kairos.backend_SmartParking.services;

import com.kairos.backend_SmartParking.dto.CarRequest;
import com.kairos.backend_SmartParking.dto.CarResponse;
import com.kairos.backend_SmartParking.entities.Cars;
import com.kairos.backend_SmartParking.entities.Users;
import com.kairos.backend_SmartParking.repositories.CarRepository;
import com.kairos.backend_SmartParking.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CarService {

    private final CarRepository carRepository;
    private final UserRepository userRepository;

    public CarService(CarRepository carRepository, UserRepository userRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    // ================= CREATE =================
    public CarResponse createCar(CarRequest request) {
        Users user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cars car = new Cars();
        car.setModele(request.getModele());
        car.setUser(user);

        car = carRepository.save(car);

        return mapToResponse(car);
    }

    // ================= READ ONE =================
    @Transactional(readOnly = true)
    public CarResponse getCar(UUID id) {
        Cars car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        return mapToResponse(car);
    }

    // ================= READ ALL =================
    @Transactional(readOnly = true)
    public List<CarResponse> getAllCars() {
        return carRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    // ================= UPDATE =================
    public CarResponse updateCar(UUID id, CarRequest request) {
        Cars car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        Users user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        car.setModele(request.getModele());
        car.setUser(user);

        car = carRepository.save(car);

        return mapToResponse(car);
    }

    // ================= DELETE =================
    public void deleteCar(UUID id) {
        if (!carRepository.existsById(id)) {
            throw new RuntimeException("Car not found");
        }
        carRepository.deleteById(id);
    }

    // ================= MAPPER =================
    private CarResponse mapToResponse(Cars car) {
        return new CarResponse(
                car.getId(),
                car.getModele(),
                car.getUser().getUsername()
        );
    }
}
