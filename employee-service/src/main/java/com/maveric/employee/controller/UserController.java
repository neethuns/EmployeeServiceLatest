package com.maveric.employee.controller;

import com.maveric.employee.dto.UserDto;
import com.maveric.employee.model.JWTRequest;
import com.maveric.employee.model.JWTResponse;
import com.maveric.employee.model.LoginRequest;
import com.maveric.employee.repo.AuthorisationRepo;
import com.maveric.employee.service.UserService;
import com.maveric.employee.utility.JWTUtility;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
@RequestMapping("/api/v1/")
public class UserController {
    @Autowired
    JWTUtility jwtUtility;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;
    @Autowired
    @Lazy
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    AuthorisationRepo authorisationRepo;

    @PostMapping("/auth/signup")
    public ResponseEntity<JWTResponse> signup(@RequestBody com.maveric.employee.model.User user)
    {

        JWTRequest jwtRequest = new JWTRequest();
        jwtRequest.setEmail(user.getEmail());

        String password = bCryptPasswordEncoder.encode(user.getPassword());
        jwtRequest.setPassword(password);

        authorisationRepo.save(jwtRequest);
        user.setPassword(password);
        UserDto userDto = userService.createUser(user);

        final UserDetails userDetails = new User(user.getEmail(), password, new ArrayList<>());
        final String token = jwtUtility.generateToken(userDetails);
        return ResponseEntity.status(HttpStatus.OK).body(new JWTResponse(token, userDto));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<JWTResponse> login(@RequestBody LoginRequest loginRequest) throws Exception {
        String password = loginRequest.getPassword();
        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), password));

        } catch (BadCredentialsException ex) {
            throw new Exception("Invalid credentials");
        }

        final UserDetails userDetails = userService.loadUserByUsername(loginRequest.getEmail());
        final String token = jwtUtility.generateToken(userDetails);
        return ResponseEntity.status(HttpStatus.OK).body(new JWTResponse(token, userService.getUserDetailsByEmail(loginRequest.getEmail())));
    }
}
