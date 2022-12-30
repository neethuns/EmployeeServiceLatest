package com.maveric.employee.repo;

import com.maveric.employee.model.JWTRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorisationRepo extends JpaRepository<JWTRequest, Integer> {

    JWTRequest findByEmail(String email);
}
