package com.example.accountservice.dto;

import com.example.accountservice.entity.AccountStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AccountDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "Account status is required")
    private AccountStatus status;

    @NotBlank(message = "User is required")
    private Long userId;
}
