package com.kairos.backend_SmartParking.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String parkingId;

    @Column(nullable = false)
    private Instant comeAt;  // Date/heure de début

    @Column(nullable = false)
    private Integer durationHours; // Durée de la réservation en heures

    @Column(nullable = false)
    private Instant goAt; // Calculé à partir de comeAt + durationHours

    @PrePersist
    public void prePersist() {
        if (comeAt == null) comeAt = Instant.now();
        if (goAt == null && durationHours != null) {
            goAt = comeAt.plusSeconds(durationHours * 3600L);
        }
    }

}
