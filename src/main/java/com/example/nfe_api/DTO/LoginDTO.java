package com.example.nfe_api.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {
    @NotBlank
    private String email;

    @NotBlank
    private String senha;
}
