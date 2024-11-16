package com.project.ad_exchange.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record BillingInfoDto(
        @NotBlank(message = "Cardholder name is required")
        String cardHolderName,

        @NotBlank(message = "Card number is required")
        @Pattern(regexp = "\\d{16}", message = "Card number must be 16 digits")
        String cardNumber,

        @NotBlank(message = "Expiration month is required")
        @Pattern(regexp = "(0[1-9]|1[0-2])", message = "Expiration month must be in MM format")
        String expirationMonth,

        @NotBlank(message = "Expiration year is required")
        @Pattern(regexp = "\\d{4}", message = "Expiration year must be in YYYY format")
        String expirationYear
) {}
