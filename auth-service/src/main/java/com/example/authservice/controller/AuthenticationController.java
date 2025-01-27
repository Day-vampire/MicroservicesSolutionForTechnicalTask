package com.example.authservice.controller;


import com.example.authservice.config.UserDetailsImpl;
import com.example.authservice.dto.AuthenticationRequest;
import com.example.authservice.dto.AuthenticationResponse;
import com.example.authservice.dto.RegistrationRequest;
import com.example.authservice.dto.UserDetailsDto;
import com.example.authservice.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Registration")
    public AuthenticationResponse register(@Valid @RequestBody RegistrationRequest registrationRequest) {
        return authenticationService.register(registrationRequest);
    }

    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Authentication")
    public AuthenticationResponse authenticate(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
        return authenticationService.authenticate(authenticationRequest);
    }

    @GetMapping("/{login}")
    public UserDetailsDto GetAuthorize (@PathVariable String login){
        return authenticationService.GetAuthorize(login);
    }

}