package com.example.nfe_api.Mapper;

import com.example.nfe_api.DTO.*;
import com.example.nfe_api.Entitys.NotaFiscal;
import com.example.nfe_api.Entitys.Produto;

import java.util.List;
import java.util.stream.Collectors;

public class NotaMapper {

    public static NotaFiscalResponseDTO toResponse(NotaFiscal nota) {
        NotaFiscalResponseDTO dto = new NotaFiscalResponseDTO();
        dto.setId(nota.getId());
        dto.setNumero(nota.getNumero());
        dto.setEmitente(nota.getEmitente());

        if (nota.getProdutos() != null) {
            List<ProdutoResponseDTO> produtos = nota.getProdutos().stream()
                    .map(NotaMapper::toProdutoResponse)
                    .collect(Collectors.toList());
            dto.setProdutos(produtos);
        }

        return dto;
    }

    public static Produto toProdutoEntity(ProdutoRequestDTO dto) {
        Produto p = new Produto();
        p.setNome(dto.getNome());
        p.setPreco(dto.getPreco());
        return p;
    }

    public static ProdutoResponseDTO toProdutoResponse(Produto p) {
        ProdutoResponseDTO dto = new ProdutoResponseDTO();
        dto.setId(p.getId());
        dto.setNome(p.getNome());
        dto.setPreco(p.getPreco());
        return dto;
    }
}
