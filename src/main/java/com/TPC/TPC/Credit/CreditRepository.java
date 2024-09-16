package com.TPC.TPC.Credit;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CreditRepository extends JpaRepository<Credit, Integer> {
    Page<Credit> findByDataCredito(String credit, Pageable pageable);
}