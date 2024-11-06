package com.project.ad_exchange.Dto;

import jakarta.validation.constraints.NotBlank;

public record LoginDto(String username, String email, @NotBlank String password) {

}
