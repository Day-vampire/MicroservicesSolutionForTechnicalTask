package com.example.authservice.service;


import com.example.authservice.config.UserDetailsImpl;
import com.example.authservice.dto.AuthenticationRequest;
import com.example.authservice.dto.AuthenticationResponse;
import com.example.authservice.dto.RegistrationRequest;
import com.example.authservice.dto.UserDetailsDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {

    AuthenticationResponse register(RegistrationRequest registrationRequest);
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
    UserDetailsDto GetAuthorize(String username);
}