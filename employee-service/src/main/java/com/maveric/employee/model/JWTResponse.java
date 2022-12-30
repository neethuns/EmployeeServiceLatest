package com.maveric.employee.model;

import com.maveric.employee.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class JWTResponse {
    private String jwtToken;
    private UserDto userDto;

    public JWTResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
