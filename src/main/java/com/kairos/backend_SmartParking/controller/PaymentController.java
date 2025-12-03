package com.smartparking.payment;

import com.kairos.backend_SmartParking.entities.Payment;
import com.kairos.backend_SmartParking.enums.PaymentStatus;
import com.kairos.backend_SmartParking.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
            @PathVariable Long id,
            @RequestParam PaymentStatus status
    ) {
        return paymentService.updateStatus(id, status);
    }

    @GetMapping("/{id}")
    public Payment getPayment(@PathVariable Long id) {
        return paymentService.get(id);
    }
}
