package com.kairos.backend_SmartParking.services;

import com.kairos.backend_SmartParking.entities.Parking;
import com.kairos.backend_SmartParking.entities.Reservation;
import com.kairos.backend_SmartParking.enums.ParkingStatus;
import com.kairos.backend_SmartParking.repositories.ParkingRepository;
import com.kairos.backend_SmartParking.repositories.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ParkingRepository parkingRepository;

    /** Crée une réservation et marque le parking comme occupé */
    //plus tard on pourra attribuer automatiquement une place libre sur le parking

    public Reservation createReservation(String userId, String parkingCode, int durationHours) {
        Parking parking = parkingRepository.findById(parkingCode)
                .orElseThrow(() -> new RuntimeException("Parking introuvable"));

        if (parking.getStatus() != ParkingStatus.FREE) {
            throw new RuntimeException("Parking non disponible");
        }

        Reservation reservation = new Reservation();
        reservation.setUserId(userId);
        reservation.setParkingId(parkingCode);
        reservation.setComeAt(Instant.now());
        reservation.setDurationHours(durationHours);

        // Marquer le parking comme occupé
        parking.setStatus(ParkingStatus.OCCUPIED);
        parkingRepository.save(parking);

        return reservationRepository.save(reservation);
    }

    /** Récupère une réservation par ID */
    public Reservation findById(UUID id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réservation introuvable"));
    }

    /** Liste toutes les réservations */
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    /** Supprime une réservation et libère le parking*/
    public void deleteReservation(UUID id) {
        Reservation reservation = findById(id);
        Parking parking = parkingRepository.findById(reservation.getParkingId())
                .orElseThrow(() -> new RuntimeException("Parking introuvable"));
        parking.setStatus(ParkingStatus.FREE);
        parkingRepository.save(parking);

        reservationRepository.deleteById(id);
    }

    /** Calcule la date/heure de fin à partir de comeAt et durationHours */
    public Instant calculateEndTime(Reservation reservation) {
        return reservation.getComeAt().plusSeconds(reservation.getDurationHours() * 3600L);
    }

    /**
     * Met à jour la durée de la réservation
     */
    public Reservation updateDuration(UUID id, int newDurationHours) {
        Reservation reservation = findById(id);
        reservation.setDurationHours(newDurationHours);
        return reservationRepository.save(reservation);
    }
}
