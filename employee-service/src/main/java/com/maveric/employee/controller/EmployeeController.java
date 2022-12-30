package com.maveric.employee.controller;


import com.maveric.employee.dto.EmployeeDto;
import com.maveric.employee.model.Employee;
import com.maveric.employee.service.EmployeeServiceImpl;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    @Autowired
    public EmployeeServiceImpl employeeService;
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;


    @GetMapping("/importEmployees")
    public void importCsvToDb() {
        JobParameters jobParameters = new JobParametersBuilder().addLong("startAt", System.currentTimeMillis()).toJobParameters();
        try {
            jobLauncher.run(job, jobParameters);
        } catch (JobInstanceAlreadyCompleteException | JobExecutionAlreadyRunningException | JobParametersInvalidException | JobRestartException e) {
            e.printStackTrace();
        }

    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<List<Employee>> getEmployeeDetailsByFirstName(@RequestParam String firstName) {
        return new ResponseEntity<>(employeeService.getEmployeeDetailsByFirstName(firstName), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Employee>> getEmployeeDetailsByName(@RequestParam String startWord) {
        return new ResponseEntity<>(employeeService.getEmployeeDetailsByName(startWord), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    protected ResponseEntity<EmployeeDto> getEmployeeDetails(@PathVariable Integer id) {
        return new ResponseEntity<>(employeeService.getEmployeeDetails(id), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployeeDetails(@RequestBody EmployeeDto employeeDto, @PathVariable Integer id) {
        return new ResponseEntity<>(employeeService.updateEmployeeDetails(employeeDto, id), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) {
        return new ResponseEntity<>(employeeService.deleteEmployee(id), HttpStatus.OK);
    }
}
