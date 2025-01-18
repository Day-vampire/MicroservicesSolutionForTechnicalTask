package com.example.accountservice.dto;

import com.example.accountservice.entity.AccountStatus;
import lombok.Data;

@Data
public class AccountFilterDto {
    private Long userId;
    private AccountStatus status;
}
