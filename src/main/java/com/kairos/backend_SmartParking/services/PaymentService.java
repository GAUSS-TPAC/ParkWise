package com.kairos.backend_SmartParking.services;

import com.kairos.backend_SmartParking.dto.PaymentCreateRequest;
import com.kairos.backend_SmartParking.entities.Parking;
import com.kairos.backend_SmartParking.repositories.ParkingRepository;
import com.kairos.backend_SmartParking.repositories.PaymentRepository;
import com.kairos.backend_SmartParking.entities.Payment;
import com.kairos.backend_SmartParking.enums.PaymentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ParkingRepository parkingRepository;

    public Payment create(PaymentCreateRequest request, String code) {

        // Récupérer le parking pour connaître le prix par heure
        Parking parking = parkingRepository.findById(code)
                .orElseThrow(() -> new RuntimeException("Parking introuvable"));

        // Calcul du montant
        int amount = (int) (request.getDurationHours() * parking.getPricePerHour());

        Payment payment = new Payment(
                request.getUserId(),
                amount,
                request.getModePaiement()
        );

        payment.setPaymentStatus(PaymentStatus.PENDING);

        return paymentRepository.save(payment);
    }



    public Payment updateStatus(UUID id, PaymentStatus status) {
        Payment p = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paiement introuvable"));
        p.setPaymentStatus(status);
        return paymentRepository.save(p);
    }

    public Payment get(UUID id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paiement introuvable"));
    }
}
