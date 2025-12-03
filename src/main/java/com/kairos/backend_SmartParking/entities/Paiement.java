package com.kairos.backend_SmartParking.entities;

import com.kairos.backend_SmartParking.enums.ModePaiement;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Paiement {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "mode_paiement", nullable = false)
    private ModePaiement modePaiement;

    // Constructeur par défaut
    public Paiement() {
        this.id = UUID.randomUUID();
    }

    // Constructeur complet
    public Paiement(String userId, ModePaiement modePaiement) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.modePaiement = modePaiement;
    }

    // GETTERS & SETTERS
    public UUID getId() {
        return id;
    }

    // inutile de setter un UUID → on bloque
    public void setId(UUID id) {
        this.id = id;
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
}
