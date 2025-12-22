package com.kairos.backend_SmartParking.controllers;

import com.kairos.backend_SmartParking.entities.Reservation;
import com.kairos.backend_SmartParking.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    /** Crée une réservation */
    @PostMapping
    public ResponseEntity<Reservation> createReservation(
            @RequestParam String userId,
            @RequestParam String parkingCode,
            @RequestParam int durationHours
    ) {
        Reservation reservation = reservationService.createReservation(userId, parkingCode, durationHours);
        return ResponseEntity.ok(reservation);
    }

    /** Récupère toutes les réservations */
    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.findAll();
        return ResponseEntity.ok(reservations);
    }

    /** Récupère une réservation par ID */
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservation(@PathVariable UUID id) {
        Reservation reservation = reservationService.findById(id);
        return ResponseEntity.ok(reservation);
    }

    /** Supprime une réservation */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable UUID id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }

    /** Met à jour la durée d'une réservation */
    @PutMapping("/{id}/duration")
    public ResponseEntity<Reservation> updateDuration(
            @PathVariable UUID id,
            @RequestParam int newDurationHours
    ) {
        Reservation reservation = reservationService.updateDuration(id, newDurationHours);
        return ResponseEntity.ok(reservation);
    }

    /** Calcule la date de fin d'une réservation */
    @GetMapping("/{id}/end-time")
    public ResponseEntity<Instant> getEndTime(@PathVariable UUID id) {
        Reservation reservation = reservationService.findById(id);
        Instant endTime = reservationService.calculateEndTime(reservation);
        return ResponseEntity.ok(endTime);
    }
}
