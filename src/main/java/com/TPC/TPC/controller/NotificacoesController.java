package com.TPC.TPC.controller;

import com.TPC.TPC.model.Notificacoes;
import com.TPC.TPC.repository.NotificacoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("notificacoes")
@CacheConfig(cacheNames = "notificacoes")
public class NotificacoesController {

    @Autowired
    private NotificacoesRepository notificacoesRepository;

    // Buscar todas as notificações
    @GetMapping
    @Cacheable("notificacoes")
    public Page<Notificacoes> listarNotificacoes(
        @RequestParam(required = false) String notificacoes,
        @PageableDefault(sort = "dataEnvio", direction = Direction.ASC  ) Pageable pageable
    ) {
        if (notificacoes != null){
            return notificacoesRepository.findByDataEnvio(notificacoes, pageable);
        }
        return notificacoesRepository.findAll(pageable);
    }

    // Buscar uma notificação pelo ID
    @GetMapping("{notificacoesID}")
    public ResponseEntity<Notificacoes> getNotificacaoById(@PathVariable Integer notificacoesID) {
        return notificacoesRepository.findById(notificacoesID)
                .map(notificacao -> ResponseEntity.ok().body(notificacao))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Criar uma nova notificação
    @PostMapping
    @CacheEvict(allEntries = true)
    public ResponseEntity<Notificacoes> createNotificacao(@Valid @RequestBody Notificacoes notificacao) {
        Notificacoes savedNotificacao = notificacoesRepository.save(notificacao);
        return new ResponseEntity<>(savedNotificacao, HttpStatus.CREATED);
    }

    // Atualizar uma notificação existente
    @PutMapping("{notificacoesID}")
    @CacheEvict(allEntries = true)
    public ResponseEntity<Notificacoes> updateNotificacao(@PathVariable Integer notificacoesID, @Valid @RequestBody Notificacoes notificacaoDetails) {
        return notificacoesRepository.findById(notificacoesID)
                .map(notificacao -> {
                    notificacao.setPdvID(notificacaoDetails.getPdvID());
                    notificacao.setTitulo(notificacaoDetails.getTitulo());
                    notificacao.setMensagem(notificacaoDetails.getMensagem());
                    notificacao.setDataEnvio(notificacaoDetails.getDataEnvio());
                    Notificacoes updatedNotificacao = notificacoesRepository.save(notificacao);
                    return ResponseEntity.ok().body(updatedNotificacao);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Deletar uma notificação
    @DeleteMapping("{notificacoesID}")
    @CacheEvict(allEntries = true)
    public ResponseEntity<?> deleteNotificacao(@PathVariable Integer notificacoesID) {
        return notificacoesRepository.findById(notificacoesID)
                .map(notificacao -> {
                    notificacoesRepository.delete(notificacao);
                    return ResponseEntity.ok().build();
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
