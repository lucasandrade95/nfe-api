package com.example.nfe_api.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NotaFiscalRequestDTO {
    @NotBlank
    private String numero;

    @NotBlank
    private String emitente;
}
