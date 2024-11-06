package com.project.ad_exchange.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserUpdateDto(
        @Email String email,
        @Size(min = 3, max = 50) String username,
        @Size(min = 6, max = 50) String password
) {}
