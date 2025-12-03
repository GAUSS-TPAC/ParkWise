package com.kairos.backend_SmartParking.repositories;

import com.kairos.backend_SmartParking.entities.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParkingRepository extends JpaRepository<Parking, UUID> {
    boolean existsByCode(String code);
}
