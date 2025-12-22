package com.kairos.backend_SmartParking.dto;

import com.kairos.backend_SmartParking.enums.ModePaiement;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class PaymentCreateRequest {

    @NotBlank
    private UUID userId;

    @NotNull
    @Min(1)
    private Integer durationHours;

    @NotNull
    private ModePaiement modePaiement;

    // getters & setters
    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Integer getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(Integer durationHours) {
        this.durationHours = durationHours;
    }

    public ModePaiement getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(ModePaiement modePaiement) {
        this.modePaiement = modePaiement;
    }
}
