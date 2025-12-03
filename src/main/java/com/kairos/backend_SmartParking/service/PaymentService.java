package com.kairos.backend_SmartParking.service;

import com.kairos.backend_SmartParking.Repository.PaymentRepository;
import com.kairos.backend_SmartParking.entities.Payment;
import com.kairos.backend_SmartParking.enums.PaymentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public Payment create(Payment payment) {
        payment.setPaymentStatus(PaymentStatus.PENDING);
        return paymentRepository.save(payment);
    }

    public Payment updateStatus(Long id, PaymentStatus status) {
        Payment p = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paiement introuvable"));
        p.setPaymentStatus(status);
        return paymentRepository.save(p);
    }

    public Payment get(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paiement introuvable"));
    }
}
