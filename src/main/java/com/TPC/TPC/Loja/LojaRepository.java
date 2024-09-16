package com.TPC.TPC.Loja;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface LojaRepository extends JpaRepository<Loja, Integer> {
    Page<Loja> findByNomeLoja(String loja, Pageable pageable);
}