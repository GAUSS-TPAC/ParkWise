package com.kairos.backend_SmartParking.Repository;

import com.kairos.backend_SmartParking.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, String> {
    Optional<Users> findById(String id);
    List<Users> findAll();
    Optional<Users> findByUserName(String username);
}
