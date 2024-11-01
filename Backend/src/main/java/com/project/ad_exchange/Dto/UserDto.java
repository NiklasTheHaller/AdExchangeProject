package com.project.ad_exchange.Dto;

import com.project.ad_exchange.Model.AccountType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserDto(
        @NotBlank(message = "Username is required") String username,
        @NotBlank(message = "Email is required") @Email(message = "Email should be valid") String email,
        @NotBlank(message = "Password is required") String password,
        @NotBlank(message = "Account type is required") AccountType accountType
) {}
