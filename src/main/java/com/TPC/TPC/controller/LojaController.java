package com.TPC.TPC.controller;

import com.TPC.TPC.model.Loja;
import com.TPC.TPC.repository.LojaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("lojas")
@CacheConfig(cacheNames = "lojas")
public class LojaController {

    @Autowired
    private LojaRepository lojaRepository;

    // Buscar todas as lojas
    @GetMapping
    @Cacheable("lojas")
    public ResponseEntity<List<Loja>> getAllLojas() {
        List<Loja> lojas = lojaRepository.findAll();
        return ResponseEntity.ok().body(lojas);
    }

    // Buscar uma loja pelo ID
    @GetMapping("{pdvID}")
    public ResponseEntity<Loja> getLojaById(@PathVariable Integer pdvID) {
        return lojaRepository.findById(pdvID)
                .map(loja -> ResponseEntity.ok().body(loja))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Criar uma nova loja
    @PostMapping
    @CacheEvict(allEntries = true)
    public ResponseEntity<Loja> createLoja(@Valid @RequestBody Loja loja) {
        Loja savedLoja = lojaRepository.save(loja);
        return new ResponseEntity<>(savedLoja, HttpStatus.CREATED);
    }

    // Atualizar uma loja existente
    @PutMapping("{pdvID}")
    @CacheEvict(allEntries = true)
    public ResponseEntity<Loja> updateLoja(@PathVariable Integer pdvID, @Valid @RequestBody Loja lojaDetails) {
        return lojaRepository.findById(pdvID)
                .map(loja -> {
                    loja.setNomeLoja(lojaDetails.getNomeLoja());
                    loja.setEndereco(lojaDetails.getEndereco());
                    loja.setNumero(lojaDetails.getNumero());
                    loja.setComplemento(lojaDetails.getComplemento());
                    loja.setCep(lojaDetails.getCep());
                    loja.setActive(lojaDetails.isActive());
                    Loja updatedLoja = lojaRepository.save(loja);
                    return ResponseEntity.ok().body(updatedLoja);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Deletar uma loja
    @DeleteMapping("{pdvID}")
    @CacheEvict(allEntries = true)
    public ResponseEntity<?> deleteLoja(@PathVariable Integer pdvID) {
        return lojaRepository.findById(pdvID)
                .map(loja -> {
                    lojaRepository.delete(loja);
                    return ResponseEntity.ok().build();
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
