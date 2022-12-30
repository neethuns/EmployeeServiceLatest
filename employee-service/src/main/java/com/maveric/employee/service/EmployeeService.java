package com.maveric.employee.service;

import com.maveric.employee.dto.EmployeeDto;
import com.maveric.employee.model.Employee;

import java.util.List;


public interface EmployeeService {
    List<Employee> getAllEmployees();

    EmployeeDto getEmployeeDetails(Integer id);

    List<Employee> getEmployeeDetailsByFirstName(String firstName);

    List<Employee> getEmployeeDetailsByName(String startWord);

    EmployeeDto updateEmployeeDetails(EmployeeDto employee, Integer id);

    String deleteEmployee(Integer id);
}
