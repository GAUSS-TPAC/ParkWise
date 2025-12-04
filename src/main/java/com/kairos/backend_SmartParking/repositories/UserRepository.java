package com.kairos.backend_SmartParking.repositories;

import com.kairos.backend_SmartParking.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<Users, UUID> {
    boolean existsByUsername(String username);
}
