package com.kairos.backend_SmartParking.controllers;

import com.kairos.backend_SmartParking.entities.Reservation;
import com.kairos.backend_SmartParking.services.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public Reservation create(@RequestBody Reservation reservation) {
        return reservationService.create(reservation);
    }

    @GetMapping
    public List<Reservation> getAll() {
        return reservationService.findAll();
    }

    @GetMapping("/{id}")
    public Reservation getById(@PathVariable UUID id) {
        return reservationService.findById(id);
    }

    @PutMapping("/{id}/dates")
    public Reservation updateDates(
            @PathVariable UUID id,
            @RequestParam Instant comeAt,
            @RequestParam LocalDateTime goAt
    ) {
        return reservationService.updateDates(id, comeAt, goAt);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        reservationService.delete(id);
    }
}
