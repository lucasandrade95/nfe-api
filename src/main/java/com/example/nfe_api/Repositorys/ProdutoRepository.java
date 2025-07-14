package com.example.nfe_api.Repositorys;

import com.example.nfe_api.Entitys.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
