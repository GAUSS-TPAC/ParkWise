package com.kairos.backend_SmartParking.services;

import com.kairos.backend_SmartParking.entities.Parking;
import com.kairos.backend_SmartParking.enums.ParkingStatus;
import com.kairos.backend_SmartParking.repositories.ParkingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ParkingService {

    private final ParkingRepository parkingRepository;

    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    public Parking create(Parking parking) {
        if (parkingRepository.existsByCode(parking.getCode())) {
            throw new IllegalArgumentException("Ce code de parking existe déjà.");
        }
        parking.setStatus(ParkingStatus.FREE);
        return parkingRepository.save(parking);
    }

    public List<Parking> findAll() {
        return parkingRepository.findAll();
    }

    public Parking findById(UUID id) {
        return parkingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parking introuvable"));
    }

    public Parking changeStatus(UUID id, ParkingStatus status) {
        Parking parking = findById(id);
        parking.setStatus(status);
        return parkingRepository.save(parking);
    }

    public void delete(UUID id) {
        parkingRepository.deleteById(id);
    }
}
