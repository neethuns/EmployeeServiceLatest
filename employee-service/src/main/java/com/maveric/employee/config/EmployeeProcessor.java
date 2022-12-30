package com.maveric.employee.config;

import com.maveric.employee.model.Employee;
import org.springframework.batch.item.ItemProcessor;


public class EmployeeProcessor implements ItemProcessor<Employee, Employee> {

    @Override
    public Employee process(Employee employee) {
        return employee;
    }

}
