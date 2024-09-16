package com.TPC.TPC.Notificacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NotificacoesService {
    @Autowired
    private NotificacoesRepository notificacoesRepository;

    public Page<Notificacoes> listarNotificacoes(String notificacoes, Pageable pageable) {
        if (notificacoes != null) {
            return notificacoesRepository.findByDataEnvio(notificacoes, pageable);
        }
        return notificacoesRepository.findAll(pageable);
    }

    public ResponseEntity<Notificacoes> getNotificacoesById(Integer notificacoesID) {
        return notificacoesRepository.findById(notificacoesID)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Notificacoes> createNotificacoes(Notificacoes notificacoes) {
        Notificacoes savedNotificacoes = notificacoesRepository.save(notificacoes);
        return new ResponseEntity<>(savedNotificacoes, HttpStatus.CREATED);
    }

    public ResponseEntity<Notificacoes> updateNotificacoes(Integer notificacoesID, Notificacoes notificacoesDetails) {
        return notificacoesRepository.findById(notificacoesID)
                .map(notificacao -> {
                    notificacao.setPdvID(notificacoesDetails.getPdvID());
                    notificacao.setTitulo(notificacoesDetails.getTitulo());
                    notificacao.setMensagem(notificacoesDetails.getMensagem());
                    notificacao.setDataEnvio(notificacoesDetails.getDataEnvio());
                    Notificacoes updatedNotificacoes = notificacoesRepository.save(notificacao);
                    return ResponseEntity.ok(updatedNotificacoes);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<?> deleteNotificacoes(Integer notificacoesID) {
        return notificacoesRepository.findById(notificacoesID)
                .map(notificacao -> {
                    notificacoesRepository.delete(notificacao);
                    return ResponseEntity.ok().build();
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
