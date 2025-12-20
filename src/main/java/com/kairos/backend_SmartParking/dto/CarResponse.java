package com.kairos.backend_SmartParking.dto;

import java.util.UUID;

public record CarResponse(
        UUID id,
        String modele
) {}

