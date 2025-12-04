package com.kairos.backend_SmartParking.services;

import com.kairos.backend_SmartParking.entities.Reservation;
import com.kairos.backend_SmartParking.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation create(Reservation reservation) {
        if (reservation.getGoAt() == null || reservation.getComeAt() == null) {
            throw new IllegalArgumentException("Les dates must be defined.");
        }
        return reservationRepository.save(reservation);
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public Reservation findById(UUID id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réservation introuvable"));
    }

    public Reservation updateDates(UUID id, Instant comeAt, LocalDateTime goAt) {
        Reservation r = findById(id);
        r.setComeAt(comeAt);
        r.setGoAt(goAt);
        return reservationRepository.save(r);
    }

    public void delete(UUID id) {
        reservationRepository.deleteById(id);
    }
}
