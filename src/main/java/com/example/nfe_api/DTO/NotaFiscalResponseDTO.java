package com.example.nfe_api.DTO;

import lombok.Data;
import java.util.List;

@Data
public class NotaFiscalResponseDTO {
    private Long id;
    private String numero;
    private String emitente;
    private String descricao;
    private List<ProdutoResponseDTO> produtos;
}
