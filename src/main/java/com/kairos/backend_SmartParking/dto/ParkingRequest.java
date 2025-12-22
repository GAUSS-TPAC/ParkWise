package com.kairos.backend_SmartParking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ParkingRequest(
        @NotBlank String code,
        @NotBlank String zone,
        @NotNull Double pricePerHour
) {}

