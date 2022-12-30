package com.maveric.employee.service;

import com.maveric.employee.dto.UserDto;
import com.maveric.employee.model.JWTRequest;
import com.maveric.employee.repo.AuthorisationRepo;
import com.maveric.employee.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    AuthorisationRepo authorisationRepo;
    @Autowired
    UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        JWTRequest jwtRequest = authorisationRepo.findByEmail(email);
        return new User(jwtRequest.getEmail(), jwtRequest.getPassword(), new ArrayList<>());
    }

    @Override
    public UserDto createUser(com.maveric.employee.model.User user) {
        userRepo.save(user);
        return new UserDto(user.getId(), user.getFirstName(), user.getMiddleName(), user.getLastName(), user.getAddress(), user.getPhoneNumber(), user.getEmail());
    }

    @Override
    public UserDto getUserDetailsByEmail(String email) {
        com.maveric.employee.model.User user = userRepo.findByEmail(email);
        return new UserDto(user.getId(), user.getFirstName(), user.getMiddleName(), user.getLastName(), user.getAddress(), user.getPhoneNumber(), user.getEmail());

    }

}
