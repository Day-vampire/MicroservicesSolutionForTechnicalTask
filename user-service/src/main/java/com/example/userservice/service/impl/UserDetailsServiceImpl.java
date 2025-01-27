package com.example.userservice.service.impl;

import com.example.userservice.client.AuthServiceClient;
import com.example.userservice.config.UserDetailsDto;
import com.example.userservice.config.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
