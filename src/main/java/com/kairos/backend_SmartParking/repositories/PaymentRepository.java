package com.kairos.backend_SmartParking.repositories;

import com.kairos.backend_SmartParking.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
