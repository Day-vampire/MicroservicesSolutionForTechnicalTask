package com.example.authservice.service.impl;

import com.example.authservice.config.UserDetailsImpl;
import com.example.authservice.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PersonRepository personRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetailsImpl loadUserByUsername(String login) throws UsernameNotFoundException {
        return personRepository.findByLogin(login)
                .map(UserDetailsImpl::new)
                .orElseThrow(() -> new UsernameNotFoundException("Person with login: %s not found".formatted(login)));
    }
}
