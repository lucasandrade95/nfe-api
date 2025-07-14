package com.example.nfe_api.Controllers;

import com.example.nfe_api.DTO.NotaFiscalCreateDTO;
import com.example.nfe_api.DTO.NotaFiscalResponseDTO;
import com.example.nfe_api.DTO.ProdutoRequestDTO;
import com.example.nfe_api.DTO.ProdutoResponseDTO;
import com.example.nfe_api.Entitys.NotaFiscal;
import com.example.nfe_api.Entitys.Produto;
import com.example.nfe_api.Mapper.NotaMapper;
import com.example.nfe_api.Repositorys.NotaFiscalRepository;
import com.example.nfe_api.Repositorys.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notas")
@RequiredArgsConstructor
public class NotaFiscalController {

    private final NotaFiscalRepository notaRepo;
    private final ProdutoRepository produtoRepo;

    @PostMapping
    public ResponseEntity<NotaFiscalResponseDTO> createNota(@RequestBody NotaFiscalCreateDTO dto) {
        NotaFiscal nota = new NotaFiscal();
        nota.setNumero(dto.getNumero());
        nota.setEmitente(dto.getEmitente());
        nota.setDescricao(dto.getDescricao());

        NotaFiscal saved = notaRepo.save(nota);

        NotaFiscalResponseDTO responseDTO = new NotaFiscalResponseDTO();
        responseDTO.setId(saved.getId());
        responseDTO.setNumero(saved.getNumero());
        responseDTO.setEmitente(saved.getEmitente());
        responseDTO.setDescricao(saved.getDescricao());

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<NotaFiscalResponseDTO>> getAll() {
        List<NotaFiscal> notas = notaRepo.findAll();

        List<NotaFiscalResponseDTO> response = notas.stream().map(nota -> {
            NotaFiscalResponseDTO dto = new NotaFiscalResponseDTO();
            dto.setId(nota.getId());
            dto.setNumero(nota.getNumero());
            dto.setEmitente(nota.getEmitente());
            dto.setDescricao(nota.getDescricao());
            return dto;
        }).toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaFiscal> getById(@PathVariable Long id) {
        return notaRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotaFiscalResponseDTO> updateNota(@PathVariable Long id, @RequestBody NotaFiscalCreateDTO notaDTO) {
        return notaRepo.findById(id).map(existing -> {
            existing.setNumero(notaDTO.getNumero());
            existing.setEmitente(notaDTO.getEmitente());
            existing.setDescricao(notaDTO.getDescricao());
            NotaFiscal updated = notaRepo.save(existing);
            return ResponseEntity.ok(NotaMapper.toResponse(updated));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNota(@PathVariable Long id) {
        if (notaRepo.existsById(id)) {
            notaRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{idNota}/produtos")
    public ResponseEntity<ProdutoResponseDTO> addProduto(@PathVariable Long idNota, @RequestBody ProdutoRequestDTO dto) {
        return notaRepo.findById(idNota).map(nota -> {
            Produto produto = NotaMapper.toProdutoEntity(dto);
            produto.setNotaFiscal(nota);

            Produto salvo = produtoRepo.save(produto);
            return ResponseEntity.ok(NotaMapper.toProdutoResponse(salvo));
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{idNota}/produtos")
    public ResponseEntity<List<Produto>> getProdutos(@PathVariable Long idNota) {
        return notaRepo.findById(idNota)
                .map(nota -> ResponseEntity.ok(nota.getProdutos()))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/produtos/{id}")
    public ResponseEntity<ProdutoResponseDTO> updateProduto(@PathVariable Long id, @RequestBody ProdutoRequestDTO dto) {
        return produtoRepo.findById(id).map(existing -> {
            existing.setNome(dto.getNome());
            existing.setPreco(dto.getPreco());

            Produto atualizado = produtoRepo.save(existing);
            return ResponseEntity.ok(NotaMapper.toProdutoResponse(atualizado));
        }).orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        if (produtoRepo.existsById(id)) {
            produtoRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
