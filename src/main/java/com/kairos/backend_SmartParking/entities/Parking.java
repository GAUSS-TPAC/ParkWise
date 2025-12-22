package com.kairos.backend_SmartParking.entities;

import com.kairos.backend_SmartParking.enums.ParkingStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
public class Parking {

    @Id
    private String code; // ex: P-A12

    @Enumerated(EnumType.STRING)
    private ParkingStatus status; // FREE, OCCUPIED, RESERVED

    @Column(nullable = false)
    private Double pricePerHour ;

    private String zone;

    @PrePersist
    public void prePersist() {
        // Générer un code unique si null
        if (code == null || code.isEmpty()) {
            this.code = generateCode();
        }
        if (status == null) status = ParkingStatus.FREE;
    }

    private String generateCode() {
        // Exemple simple : P- + 3 lettres aléatoires + 2 chiffres
        String letters = randomString(3).toUpperCase();
        String digits = String.format("%02d", (int)(Math.random() * 100));
        return "P-" + letters + digits;
    }

    private String randomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int idx = (int)(Math.random() * chars.length());
            sb.append(chars.charAt(idx));
        }
        return sb.toString();
    }

    public Parking() {
    }

    public Parking(String code, ParkingStatus status, Double pricePerHour, String zone) {
        this.code = code;
        this.status = status;
        this.pricePerHour = pricePerHour;
        this.zone = zone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ParkingStatus getStatus() {
        return status;
    }

    public void setStatus(ParkingStatus status) {
        this.status = status;
    }

    public Double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(Double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
}
