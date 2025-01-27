package com.example.accountservice.service.impl;

import com.example.accountservice.client.AuthServiceClient;
import com.example.accountservice.config.UserDetailsDto;
import com.example.accountservice.config.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AuthServiceClient authServiceClient;

    @Override
    public UserDetailsImpl loadUserByUsername(String login) throws UsernameNotFoundException {
       UserDetailsDto userDetailsDto = authServiceClient.GetAuthorize(login);
       return new UserDetailsImpl(userDetailsDto.username(), userDetailsDto.password(),userDetailsDto.roles());
    }
}
