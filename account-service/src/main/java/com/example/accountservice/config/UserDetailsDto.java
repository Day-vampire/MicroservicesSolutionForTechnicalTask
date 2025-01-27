package com.example.accountservice.config;

import com.example.accountservice.entity.Role;

import java.util.List;

public record UserDetailsDto(
     String username,
     String password,
     List <Role> roles)
{}
