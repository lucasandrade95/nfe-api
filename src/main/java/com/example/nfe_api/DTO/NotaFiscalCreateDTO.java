package com.example.nfe_api.DTO;

import lombok.Data;

@Data
public class NotaFiscalCreateDTO {
    private String numero;
    private String emitente;
    private String descricao;
}
