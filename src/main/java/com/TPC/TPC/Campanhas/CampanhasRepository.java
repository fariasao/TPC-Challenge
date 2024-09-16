package com.TPC.TPC.Campanhas;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CampanhasRepository extends JpaRepository<Campanhas, Integer> {
    Page<Campanhas> findByTitulo(String campanha, Pageable pageable);
}
