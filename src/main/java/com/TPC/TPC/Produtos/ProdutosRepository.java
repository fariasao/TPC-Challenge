package com.TPC.TPC.Produtos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProdutosRepository extends JpaRepository<Produtos, Integer> {
    Page<Produtos> findByValor(String produtos, Pageable pageable);
}