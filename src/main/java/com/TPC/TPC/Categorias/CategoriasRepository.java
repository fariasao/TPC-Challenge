package com.TPC.TPC.Categorias;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CategoriasRepository extends JpaRepository<Categorias, Integer> {
    Page<Categorias> findByNome(String categorias, Pageable pageable);
}