package com.kairos.backend_SmartParking.services;

import com.kairos.backend_SmartParking.dto.ParkingRequest;
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

    // ================= CREATE =================
    public Parking create(ParkingRequest request) {

        if (parkingRepository.existsByCode(request.code())) {
            throw new IllegalArgumentException("Ce code de parking existe déjà.");
        }

        Parking parking = new Parking();
        parking.setCode(request.code());
        parking.setZone(request.zone());
        parking.setPricePerHour(request.pricePerHour());
        parking.setStatus(ParkingStatus.FREE);

        return parkingRepository.save(parking);
    }

    // ================= READ ALL =================
    public List<Parking> findAll() {
        return parkingRepository.findAll();
    }

    // ================= READ ONE =================
    public Parking findById(UUID id) {
        return parkingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parking introuvable"));
    }

    //================== READ STATUS ===============
    public ParkingStatus getStatus(UUID id) {
        Parking parking = findById(id);
        return parking.getStatus();
    }


    // ================= UPDATE STATUS =================
    public Parking changeStatus(UUID id, ParkingStatus status) {
        Parking parking = findById(id);
        parking.setStatus(status);
        return parkingRepository.save(parking);
    }

    // ================= DELETE =================
    public void delete(UUID id) {
        parkingRepository.deleteById(id);
    }
}
