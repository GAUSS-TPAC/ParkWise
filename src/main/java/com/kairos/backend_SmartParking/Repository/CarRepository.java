package com.kairos.backend_SmartParking.Repository;

import com.kairos.backend_SmartParking.entities.Cars;
import com.kairos.backend_SmartParking.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Cars, Long> {
    Optional<Cars> findById(Long id);
    List<Cars> findAll();
    List<Cars> findByUser(Users user);

}