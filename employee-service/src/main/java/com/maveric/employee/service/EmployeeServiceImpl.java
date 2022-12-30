package com.maveric.employee.service;

import com.maveric.employee.dto.EmployeeDto;
import com.maveric.employee.exception.EmployeeNotFoundException;
import com.maveric.employee.model.Employee;
import com.maveric.employee.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    public EmployeeRepository employeeRepo;

    @Override
    public List<Employee> getAllEmployees() {

        List<Employee> employeeList = employeeRepo.findAll();
        Stream<Employee> employeeStream = employeeList.stream().sorted(Comparator.comparingInt(Employee::getId));
        return employeeStream.toList();
    }

    public EmployeeDto getEmployeeDetails(Integer id) {
        Optional<Employee> employeeSelected = employeeRepo.findById(id);

        if (employeeSelected.isPresent()) {
            Employee employee = employeeSelected.get();
            return new EmployeeDto(employee.getId(), employee.getFirstName(), employee.getMiddleName(), employee.getLastName(), employee.getAddress(), employee.getPhoneNumber(), employee.getEmail());
        } else {
            throw new EmployeeNotFoundException("Employee Not found");
        }

    }


    @Override
    public EmployeeDto updateEmployeeDetails(EmployeeDto employee, Integer id) {

        Optional<Employee> employeeSelected = employeeRepo.findById(id);
        if (employeeSelected.isPresent()) {
            Employee employeeUpdate = employeeSelected.get();

            employeeUpdate.setFirstName(employee.getFirstName());
            employeeUpdate.setMiddleName(employee.getMiddleName());
            employeeUpdate.setLastName(employee.getLastName());
            employeeUpdate.setAddress(employee.getAddress());
            employeeUpdate.setPhoneNumber(employee.getPhoneNumber());
            employeeUpdate.setEmail(employee.getEmail());
            employeeRepo.save(employeeUpdate);
            return new EmployeeDto(employeeUpdate.getId(), employeeUpdate.getFirstName(), employeeUpdate.getMiddleName(), employeeUpdate.getLastName(), employeeUpdate.getAddress(), employeeUpdate.getPhoneNumber(), employeeUpdate.getEmail());
        } else {
            throw new EmployeeNotFoundException("Employee not found");
        }

    }

    @Override
    public String deleteEmployee(Integer id) {
        if (employeeRepo.findById(id).isPresent()) {
            employeeRepo.deleteById(id);

            return "Delete Successfully .Id: " + id;
        } else {
            throw new EmployeeNotFoundException("Employee ot found");
        }
    }

    @Override
    public List<Employee> getEmployeeDetailsByFirstName(String firstName) {
        Optional<List<Employee>> employee = employeeRepo.findByFirstName(firstName);
        if (employee.isPresent()) {

            return employee.get();
        } else {
            throw new EmployeeNotFoundException("Employee not found");
        }
    }

    @Override
    public List<Employee> getEmployeeDetailsByName(String startWord) {
        List<Employee> employee = employeeRepo.findAll().stream().filter(e -> e.getFirstName().startsWith(startWord)).collect((Collectors.toList()));
        Optional<List<Employee>> employeeList = Optional.of(employee);
        return employeeList.get();
    }
}
