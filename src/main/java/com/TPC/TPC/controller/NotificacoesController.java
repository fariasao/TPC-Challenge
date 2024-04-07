package com.TPC.TPC.controller;

import com.TPC.TPC.model.Notificacoes;
import com.TPC.TPC.repository.NotificacoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/notificacoes")
public class NotificacoesController {

    @Autowired
    private NotificacoesRepository notificacoesRepository;

    // Buscar todas as notificações
    @GetMapping
    public ResponseEntity<List<Notificacoes>> getAllNotificacoes() {
        List<Notificacoes> notificacoes = notificacoesRepository.findAll();
        return ResponseEntity.ok().body(notificacoes);
    }

    // Buscar uma notificação pelo ID
    @GetMapping("/{mensagemId}")
    public ResponseEntity<Notificacoes> getNotificacaoById(@PathVariable Integer mensagemId) {
        return notificacoesRepository.findById(mensagemId)
                .map(notificacao -> ResponseEntity.ok().body(notificacao))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Criar uma nova notificação
    @PostMapping
    public ResponseEntity<Notificacoes> createNotificacao(@Valid @RequestBody Notificacoes notificacao) {
        Notificacoes savedNotificacao = notificacoesRepository.save(notificacao);
        return new ResponseEntity<>(savedNotificacao, HttpStatus.CREATED);
    }

    // Atualizar uma notificação existente
    @PutMapping("/{mensagemId}")
    public ResponseEntity<Notificacoes> updateNotificacao(@PathVariable Integer mensagemId, @Valid @RequestBody Notificacoes notificacaoDetails) {
        return notificacoesRepository.findById(mensagemId)
                .map(notificacao -> {
                    notificacao.setPdvId(notificacaoDetails.getPdvId());
                    notificacao.setTitulo(notificacaoDetails.getTitulo());
                    notificacao.setMensagem(notificacaoDetails.getMensagem());
                    notificacao.setDataEnvio(notificacaoDetails.getDataEnvio());
                    Notificacoes updatedNotificacao = notificacoesRepository.save(notificacao);
                    return ResponseEntity.ok().body(updatedNotificacao);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Deletar uma notificação
    @DeleteMapping("/{mensagemId}")
    public ResponseEntity<?> deleteNotificacao(@PathVariable Integer mensagemId) {
        return notificacoesRepository.findById(mensagemId)
                .map(notificacao -> {
                    notificacoesRepository.delete(notificacao);
                    return ResponseEntity.ok().build();
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
