package com.kairos.backend_SmartParking.repositories;

import com.kairos.backend_SmartParking.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
}
