package com.kairos.backend_SmartParking.controllers;

import com.kairos.backend_SmartParking.entities.Payment;
import com.kairos.backend_SmartParking.enums.PaymentStatus;
import com.kairos.backend_SmartParking.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public Payment createPayment(@RequestBody Payment payment) {
        return paymentService.create(payment);
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
