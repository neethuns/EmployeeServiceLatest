package com.maveric.employee.service;

import com.maveric.employee.dto.EmployeeDto;
import com.maveric.employee.model.Employee;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface IntegrationGateWay {
    @Gateway(requestChannel = "integration.gateway.channel")
    String sendMessage(String message);

    @Gateway(requestChannel = "employee.gateway.channel")
    EmployeeDto processEmployeeDetails(EmployeeDto employeeDto);

}
