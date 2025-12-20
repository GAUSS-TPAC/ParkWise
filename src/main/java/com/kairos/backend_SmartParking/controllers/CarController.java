package com.kairos.backend_SmartParking.controllers;

import com.kairos.backend_SmartParking.dto.CarRequest;
import com.kairos.backend_SmartParking.entities.Cars;
import com.kairos.backend_SmartParking.services.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<Cars> createCar(@RequestBody CarRequest car) {
        return ResponseEntity.ok(carService.createCar(car));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cars> getCar(@PathVariable UUID id) {
        return ResponseEntity.ok(carService.getCar(id));
    }

    @GetMapping
    public ResponseEntity<List<Cars>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cars> updateCar(@PathVariable UUID id, @RequestBody CarRequest car) {
        return ResponseEntity.ok(carService.updateCar(id, car));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable UUID id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
}
