package com.kairos.backend_SmartParking.dto;

import java.util.UUID;

public class CarRequest {
    private String modele;
    private UUID userId;

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
