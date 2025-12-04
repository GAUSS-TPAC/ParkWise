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
    private LocalDateTime goAt; // Date/heure de fin prévue

    @PrePersist
    public void prePersist() {
        if (comeAt == null) comeAt = Instant.now();
    }
}
