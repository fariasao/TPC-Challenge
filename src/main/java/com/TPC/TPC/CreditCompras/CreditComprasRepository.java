package com.TPC.TPC.CreditCompras;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditComprasRepository extends JpaRepository<CreditCompras, Integer> {
    Page<CreditCompras> findByCreditCompraID(String creditCompras, Pageable pageable);
}