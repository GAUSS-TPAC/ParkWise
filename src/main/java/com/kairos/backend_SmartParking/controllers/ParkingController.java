package com.kairos.backend_SmartParking.controllers;

import com.kairos.backend_SmartParking.dto.ParkingRequest;
import com.kairos.backend_SmartParking.entities.Parking;
import com.kairos.backend_SmartParking.enums.ParkingStatus;
import com.kairos.backend_SmartParking.services.ParkingService;
import jakarta.validation.Valid;
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

    // ================= CREATE =================
    @PostMapping
    public Parking create(@RequestBody @Valid ParkingRequest request) {
        return parkingService.create(request);
    }

    // ================= READ ALL =================
    @GetMapping
    public List<Parking> getAll() {
        return parkingService.findAll();
    }

    // ================= READ ONE =================
    @GetMapping("/{id}")
    public Parking getById(@PathVariable UUID id) {
        return parkingService.findById(id);
    }

    // ================= UPDATE STATUS =================
    @PutMapping("/{id}/status")
    public Parking updateStatus(
            @PathVariable UUID id,
            @RequestParam ParkingStatus status
    ) {
        return parkingService.changeStatus(id, status);
    }

    //==================  READ STATUS ===============
    @GetMapping("/{id}/status")
    public ParkingStatus getStatus(@PathVariable UUID id) {
        return parkingService.getStatus(id);
    }


    // ================= DELETE =================
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        parkingService.delete(id);
    }
}
