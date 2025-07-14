package com.example.nfe_api.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProdutoRequestDTO {
    @NotBlank
    private String nome;

    @Positive
    private double preco;

}
