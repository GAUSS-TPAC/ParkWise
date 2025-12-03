package com.kairos.backend_SmartParking.entities;

import com.kairos.backend_SmartParking.enums.ParkingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String code; // Ex : P-A12

    @Enumerated(EnumType.STRING)
    private ParkingStatus status; // FREE, OCCUPIED, RESERVED

    @Column(nullable = false)
    private Double pricePerHour;

    private String zone; // Ex : Zone A, Niveau 1

    // Pour initialiser automatiquement une place libre
    @PrePersist
    public void prePersist() {
        if (status == null) status = ParkingStatus.FREE;
    }
}
