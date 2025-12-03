package com.kairos.backend_SmartParking.entities;

import com.kairos.backend_SmartParking.enums.ModePaiement;
import com.kairos.backend_SmartParking.enums.PaymentStatus;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Payment {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "mode_paiement", nullable = false)
    private ModePaiement modePaiement;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    // Constructeur par défaut
    public Payment() {
        this.id = UUID.randomUUID();
    }

    // Constructeur complet
    public Payment(String userId, ModePaiement modePaiement) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.modePaiement = modePaiement;
    }

    // GETTERS & SETTERS
    public UUID getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ModePaiement getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(ModePaiement modePaiement) {
        this.modePaiement = modePaiement;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
