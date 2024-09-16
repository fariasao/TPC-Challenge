package com.TPC.TPC.Compras;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ComprasRepository extends JpaRepository<Compras, Integer> {
    Page<Compras> findByDataCompra(String compras, Pageable pageable);
}