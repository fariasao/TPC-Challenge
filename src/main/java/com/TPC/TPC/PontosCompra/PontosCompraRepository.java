package com.TPC.TPC.PontosCompra;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PontosCompraRepository extends JpaRepository<PontosCompra, Integer>{
    Page<PontosCompra> findByPontosCompraID(String pontosCompra, Pageable pageable);
}