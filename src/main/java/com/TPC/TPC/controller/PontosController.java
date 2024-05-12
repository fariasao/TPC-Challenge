package com.TPC.TPC.controller;

import com.TPC.TPC.model.Pontos;
import com.TPC.TPC.repository.PontosRepository;
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
@RequestMapping("pontos")
@CacheConfig(cacheNames = "pontos")
public class PontosController {

    @Autowired
    private PontosRepository pontosRepository;

    // Buscar todos os registros de pontos
    @GetMapping
    @Cacheable("pontos")
    public Page<Pontos> listarPontos(
        @RequestParam(required = false) String pontos,
        @PageableDefault(sort = "dataCredito", direction = Direction.ASC  ) Pageable pageable
    ) {
        if (pontos != null){
            return pontosRepository.findByDataCredito(pontos, pageable);
        }
        return pontosRepository.findAll(pageable);
    }
    
    // Buscar um registro de pontos pelo ID
    @GetMapping("{pointID}")
    public ResponseEntity<Pontos> getPontosById(@PathVariable Integer pointID) {
        return pontosRepository.findById(pointID)
                .map(ponto -> ResponseEntity.ok().body(ponto))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Criar um novo registro de pontos
    @PostMapping
    @CacheEvict(allEntries = true)
    public ResponseEntity<Pontos> createPontos(@Valid @RequestBody Pontos ponto) {
        Pontos savedPontos = pontosRepository.save(ponto);
        return new ResponseEntity<>(savedPontos, HttpStatus.CREATED);
    }

    // Atualizar um registro de pontos existente
    @PutMapping("{pointID}")
    @CacheEvict(allEntries = true)
    public ResponseEntity<Pontos> updatePontos(@PathVariable Integer pointID, @Valid @RequestBody Pontos pontosDetails) {
        return pontosRepository.findById(pointID)
                .map(ponto -> {
                    ponto.setValor(pontosDetails.getValor());
                    ponto.setDataCredito(pontosDetails.getDataCredito());
                    ponto.setDataExpiracao(pontosDetails.getDataExpiracao());
                    ponto.setUtilizado(pontosDetails.isUtilizado());
                    Pontos updatedPontos = pontosRepository.save(ponto);
                    return ResponseEntity.ok().body(updatedPontos);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Deletar um registro de pontos
    @DeleteMapping("{pointID}")
    @CacheEvict(allEntries = true)
    public ResponseEntity<?> deletePontos(@PathVariable Integer pointID) {
        return pontosRepository.findById(pointID)
                .map(ponto -> {
                    pontosRepository.delete(ponto);
                    return ResponseEntity.ok().build();
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
