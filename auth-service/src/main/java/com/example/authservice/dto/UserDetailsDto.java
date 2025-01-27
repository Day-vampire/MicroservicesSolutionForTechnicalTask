package com.example.authservice.dto;

import com.example.authservice.entity.Role;

import java.util.List;

public record UserDetailsDto (
     String username,
     String password,
     List <Role> roles)
{}
