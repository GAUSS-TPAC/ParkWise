package com.kairos.backend_SmartParking.repositories;

import com.kairos.backend_SmartParking.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
