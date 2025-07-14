package com.example.nfe_api.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterDTO {
    @NotBlank
    private String email;

    @NotBlank
    private String senha;
}
