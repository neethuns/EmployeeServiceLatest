package com.maveric.employee.repo;

import com.maveric.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<List<Employee>> findByFirstName(String firstName);


}
