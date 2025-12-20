package com.kairos.backend_SmartParking.dto;

import java.util.List;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String username,
        List<CarResponse> cars
) {}
