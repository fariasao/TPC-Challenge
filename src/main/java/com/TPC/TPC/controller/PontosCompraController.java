package com.TPC.TPC.controller;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TPC.TPC.model.PontosCompra;
import com.TPC.TPC.repository.PontosCompraRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("pontoscompras")
@CacheConfig(cacheNames = "pontoscompras")
public class PontosCompraController {
    
    @Autowired
    private PontosCompraRepository pontosComprasRepository;

    // Buscar todas as compras de pontos
    @GetMapping
    @Cacheable("pontoscompras")
    public Page<PontosCompra> listarPontosCompra(
        @RequestParam(required = false) String pontosCompra,
        @PageableDefault(sort = "pontosCompraID", direction = Direction.ASC  ) Pageable pageable
    ) {
        if (pontosCompra != null){
            return pontosComprasRepository.findByPontosCompraID(pontosCompra, pageable);
        }
        return pontosComprasRepository.findAll(pageable);
    }

    // Buscar uma compra de pontos pelo ID do pedido
    @GetMapping("{pontosCompraID}")
    public ResponseEntity<PontosCompra> getCompraPontosById(@PathVariable Integer pontosCompraID) {
        return pontosComprasRepository.findById(pontosCompraID)
                .map(compra -> ResponseEntity.ok().body(compra))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Criar uma nova compra de pontos
    @PostMapping
    @CacheEvict(allEntries = true)
    public ResponseEntity<PontosCompra> createCompraPontos(@Valid @RequestBody PontosCompra pontosCompra) {
        PontosCompra savedCompra = pontosComprasRepository.save(pontosCompra);
        return new ResponseEntity<>(savedCompra, HttpStatus.CREATED);
    }

    // Atualizar uma compra de pontos existente
    @PutMapping("{pontosCompraID}")
    @CacheEvict(allEntries = true)
    public ResponseEntity<PontosCompra> updateCompraPontos(@PathVariable Integer pontosCompraID, @Valid @RequestBody PontosCompra pontosCompraDetails) {
        return pontosComprasRepository.findById(pontosCompraID)
                .map((PontosCompra compra) -> {
                    compra.setCompraID(pontosCompraDetails.getCompraID());
                    compra.setPointID(pontosCompraDetails.getPointID());
                    PontosCompra updatedCompra = pontosComprasRepository.save(compra);
                    return ResponseEntity.ok().body(updatedCompra);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Deletar uma compra de pontos
    @DeleteMapping("{pontosCompraID}")
    @CacheEvict(allEntries = true)
    public ResponseEntity<?> deleteCompraPontos(@PathVariable Integer pontosCompraID) {
        return pontosComprasRepository.findById(pontosCompraID)
                .map(compra -> {
                    pontosComprasRepository.delete(compra);
                    return ResponseEntity.ok().build();
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
