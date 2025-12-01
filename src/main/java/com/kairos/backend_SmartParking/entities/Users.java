package com.kairos.backend_SmartParking.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class Users {
    @Id
    private String id;
    private String username;
    private List<Long> cars_id;
}
