package com.example.nfe_api.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LogoutRequestDTO {
    @NotBlank
    private String refreshToken;
}
