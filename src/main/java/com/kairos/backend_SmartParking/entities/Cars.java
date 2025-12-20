package com.kairos.backend_SmartParking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kairos.backend_SmartParking.dto.UserResponse;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Entity
@Table(name = "cars")
public class Cars {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(name = "modele", nullable = false)
    private String modele;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private Users user;

    public Cars() {
    }

    public Cars(UUID id, String modele, Users user) {
        this.id = id;
        this.modele = modele;
        this.user = user;
    }

    // ===== GETTERS / SETTERS =====

        public void setId(UUID id) {
            this.id = id;
        }

        public String getModele() {
            return modele;
        }

        public void setModele(String modele) {
            this.modele = modele;
        }

        public Users getUser() {
            return user;
        }

        public void setUser(Users user) {
            this.user = user;
        }
}
