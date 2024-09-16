package com.TPC.TPC.Pontos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PontosRepository extends JpaRepository<Pontos, Integer> {
    Page<Pontos> findByDataCredito(String pontos, Pageable pageable);
}