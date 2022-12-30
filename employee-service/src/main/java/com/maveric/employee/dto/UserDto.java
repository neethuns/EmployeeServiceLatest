package com.maveric.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @Id
    private Integer id;
    private String firstName;
    private String secondName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String email;
}
