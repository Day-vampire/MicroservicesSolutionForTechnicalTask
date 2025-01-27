package com.example.authservice.dto;

import jakarta.validation.constraints.NotBlank;

public record RegistrationRequest(
        @NotBlank String login,
        @NotBlank String password)
{}