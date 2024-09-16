package com.TPC.TPC.Pontos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PontosRepository extends JpaRepository<Pontos, Integer> {
    Page<Pontos> findByDataCreditado(String pontos, Pageable pageable);
}