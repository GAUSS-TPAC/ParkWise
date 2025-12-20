package com.kairos.backend_SmartParking.services;

import com.kairos.backend_SmartParking.dto.UserResponse;
import com.kairos.backend_SmartParking.entities.Cars;
import com.kairos.backend_SmartParking.entities.Users;
import com.kairos.backend_SmartParking.repositories.CarRepository;
import com.kairos.backend_SmartParking.repositories.UserRepository;
import com.kairos.backend_SmartParking.dto.CarRequest;
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
    public Cars createCar(CarRequest request) {
        Users user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cars car = new Cars();
        car.setModele(request.getModele());
        car.setUser(user);

        return carRepository.save(car);
    }

    // ================= READ ONE =================
    @Transactional(readOnly = true)
    public Cars getCar(UUID id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));
    }

    // ================= READ ALL =================
    @Transactional(readOnly = true)
    public List<Cars> getAllCars() {
        return carRepository.findAll();
    }

    // ================= UPDATE =================
    public Cars updateCar(UUID id, CarRequest request) {
        Cars car = getCar(id);

        Users user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        car.setModele(request.getModele());
        car.setUser(user);

        return carRepository.save(car);
    }

    // ================= DELETE =================
    public void deleteCar(UUID id) {
        if (!carRepository.existsById(id)) {
            throw new RuntimeException("Car not found");
        }
        carRepository.deleteById(id);
    }
}
