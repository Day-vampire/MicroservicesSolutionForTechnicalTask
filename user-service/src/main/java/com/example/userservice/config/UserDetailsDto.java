package com.example.userservice.config;

import com.example.userservice.entity.Role;

import java.util.List;

public record UserDetailsDto(
     String username,
     String password,
     List <Role> roles)
{}
