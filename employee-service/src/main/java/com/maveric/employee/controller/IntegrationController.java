package com.maveric.employee.controller;

import com.maveric.employee.dto.EmployeeDto;
import com.maveric.employee.model.Employee;
import com.maveric.employee.service.IntegrationGateWay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/integrate")
public class IntegrationController {
    @Autowired
    IntegrationGateWay integrationGateway;

    @GetMapping("/{name}")
    public String getMessageFromIntegrationService(@PathVariable String name) {
        return integrationGateway.sendMessage(name);
    }

    @PostMapping
    public EmployeeDto getEmployeeFromIntegrationService(@RequestBody EmployeeDto employeeDto) {
        return integrationGateway.processEmployeeDetails(employeeDto);
    }
}
