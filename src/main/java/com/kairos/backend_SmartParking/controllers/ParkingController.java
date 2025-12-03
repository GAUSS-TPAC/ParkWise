package com.kairos.backend_SmartParking.controllers;

import com.kairos.backend_SmartParking.entities.Parking;
import com.kairos.backend_SmartParking.enums.ParkingStatus;
import com.kairos.backend_SmartParking.services.ParkingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/parking")
public class ParkingController {

    private final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @PostMapping
    public Parking create(@RequestBody Parking parking) {
        return parkingService.create(parking);
    }

    @GetMapping
    public List<Parking> getAll() {
        return parkingService.findAll();
    }

    @GetMapping("/{id}")
    public Parking getById(@PathVariable UUID id) {
        return parkingService.findById(id);
    }

    @PutMapping("/{id}/status")
    public Parking updateStatus(
            @PathVariable UUID id,
            @RequestParam ParkingStatus status
    ) {
        return parkingService.changeStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        parkingService.delete(id);
    }
}
