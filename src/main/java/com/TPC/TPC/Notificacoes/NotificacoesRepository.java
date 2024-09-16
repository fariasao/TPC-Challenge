package com.TPC.TPC.Notificacoes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface NotificacoesRepository extends JpaRepository<Notificacoes, Integer> {
    Page<Notificacoes> findByDataEnvio(String notificacoes, Pageable pageable);
}