package com.kairos.backend_SmartParking.controllers;

import com.kairos.backend_SmartParking.dto.PaymentCreateRequest;
import com.kairos.backend_SmartParking.entities.Payment;
import com.kairos.backend_SmartParking.enums.PaymentStatus;
import com.kairos.backend_SmartParking.services.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public Payment createPayment(
            @Valid @RequestBody PaymentCreateRequest request,
            @RequestParam String parkingId
    ) {
        return paymentService.create(request, parkingId);
    }


    @PatchMapping("/{id}/status")
    public Payment updatePaymentStatus(
            @PathVariable UUID id,
            @RequestParam PaymentStatus status
    ) {
        return paymentService.updateStatus(id, status);
    }

    @GetMapping("/{id}")
    public Payment getPayment(@PathVariable UUID id) {
        return paymentService.get(id);
    }

}
