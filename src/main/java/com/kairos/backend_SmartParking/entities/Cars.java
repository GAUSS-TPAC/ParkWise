package com.kairos.backend_SmartParking.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "cars")
public class Cars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="modele", nullable = false)
    private String modele;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user; // Remplace user_id string → relation propre

    public Cars() {}

    public Cars(Long id, String modele, Users user) {
        this.id = id;
        this.modele = modele;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
