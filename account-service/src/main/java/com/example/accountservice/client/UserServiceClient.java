package com.example.accountservice.client;

import com.example.accountservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:8082/api/user-service/", path = "/users")
public interface UserServiceClient {

    @GetMapping("/{id}")
    UserDto findUserById(@PathVariable Long id);

    }
