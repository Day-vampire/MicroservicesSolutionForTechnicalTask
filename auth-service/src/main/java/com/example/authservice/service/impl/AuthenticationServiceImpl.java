package com.example.authservice.service.impl;

import com.example.authservice.config.UserDetailsImpl;
import com.example.authservice.dto.AuthenticationRequest;
import com.example.authservice.dto.AuthenticationResponse;
import com.example.authservice.dto.RegistrationRequest;
import com.example.authservice.dto.UserDetailsDto;
import com.example.authservice.entity.Person;
import com.example.authservice.entity.Role;
import com.example.authservice.exception.AlreadyExistsException;
import com.example.authservice.repository.PersonRepository;
import com.example.authservice.service.AuthenticationService;
import com.example.authservice.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final PersonRepository personRepository;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Override
    @Transactional
    public AuthenticationResponse register(RegistrationRequest registrationRequest) {
        String login = registrationRequest.login();
        if (personRepository.findByLogin(login
        ).isPresent()) {
            throw new AlreadyExistsException("Person with login: %s already exists".formatted(login));
        }
        Person person = Person.builder()
                .login(login)
                .password(passwordEncoder.encode(registrationRequest.password()))
                .role(Role.USER_ROLE)
                .build();
        personRepository.save(person);
        String token = jwtService.generateToken(new UserDetailsImpl(person));
        return new AuthenticationResponse(token);
    }

    @Override
    @Transactional(readOnly = true)
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.login(),
                        authenticationRequest.password()
                )
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.login());
        String token = jwtService.generateToken(userDetails);
        return new AuthenticationResponse(token);
    }

    @Override
    public UserDetailsDto GetAuthorize(String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        return new UserDetailsDto(
                userDetails.getUsername(),
                userDetails.getPassword(),
                userDetails
                        .getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .map(Role::valueOf)
                        .toList());
    }

}