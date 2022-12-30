package com.maveric.employee.service;

import com.maveric.employee.dto.UserDto;
import com.maveric.employee.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {


    UserDto createUser(User user);

    UserDto getUserDetailsByEmail(String email);
}
