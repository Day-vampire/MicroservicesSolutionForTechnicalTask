package com.example.accountservice.client;

import com.example.accountservice.config.UserDetailsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-service", url = "http://localhost:8083/api/auth-service/", path = "/auth")
public interface AuthServiceClient {
    @GetMapping("/{login}")
    UserDetailsDto GetAuthorize (@PathVariable String login);
}