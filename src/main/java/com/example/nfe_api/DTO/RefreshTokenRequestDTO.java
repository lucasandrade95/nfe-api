package com.example.nfe_api.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RefreshTokenRequestDTO {
    @NotBlank
    private String refreshToken;
}
