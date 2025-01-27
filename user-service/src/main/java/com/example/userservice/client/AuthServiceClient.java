package com.example.userservice.client;

import com.example.userservice.config.UserDetailsDto;
import com.example.userservice.config.UserDetailsImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-service", url = "http://localhost:8083/api/auth-service/", path = "/auth")
public interface AuthServiceClient {
    @GetMapping("/{login}")
    UserDetailsDto GetAuthorize (@PathVariable String login);
}