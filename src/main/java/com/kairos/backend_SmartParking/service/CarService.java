package com.kairos.backend_SmartParking.service;

import com.kairos.backend_SmartParking.entities.Cars;
import com.kairos.backend_SmartParking.Repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    // CREATE
    public Cars createCar(Cars car) {
        return carRepository.save(car);
    }

    // READ ONE
    public Cars getCar(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));
    }

    // READ ALL
    public List<Cars> getAllCars() {
        return carRepository.findAll();
    }

    // UPDATE
    public Cars updateCar(Long id, Cars updatedCar) {
        Cars car = getCar(id);
        car.setModele(updatedCar.getModele());
        car.setUser(updatedCar.getUser());
        return carRepository.save(car);
    }

    // DELETE
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}
