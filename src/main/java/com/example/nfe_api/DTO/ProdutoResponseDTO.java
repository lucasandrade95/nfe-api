package com.example.nfe_api.DTO;

import lombok.Data;

@Data
public class ProdutoResponseDTO {
    private Long id;
    private String nome;
    private double preco;
}
