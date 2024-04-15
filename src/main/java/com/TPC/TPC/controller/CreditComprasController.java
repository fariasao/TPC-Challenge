package com.TPC.TPC.controller;

import com.TPC.TPC.model.CreditCompras;
import com.TPC.TPC.repository.CreditComprasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/creditcompras")
public class CreditComprasController {

    @Autowired
    private CreditComprasRepository creditComprasRepository;

    // Buscar todas as compras de pontos
    @GetMapping
    public ResponseEntity<List<CreditCompras>> getAllCompraPontos() {
        List<CreditCompras> compras = creditComprasRepository.findAll();
        return ResponseEntity.ok().body(compras);
    }

    // Buscar uma compra de pontos pelo ID do pedido
    @GetMapping("/{creditCompraID}")
    public ResponseEntity<CreditCompras> getCompraPontosById(@PathVariable Integer creditCompraID) {
        return creditComprasRepository.findById(creditCompraID)
                .map(compra -> ResponseEntity.ok().body(compra))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Criar uma nova compra de pontos
    @PostMapping
    public ResponseEntity<CreditCompras> createCompraPontos(@Valid @RequestBody CreditCompras compraPontos) {
        CreditCompras savedCompra = creditComprasRepository.save(compraPontos);
        return new ResponseEntity<>(savedCompra, HttpStatus.CREATED);
    }

    // Atualizar uma compra de pontos existente
    @PutMapping("/{creditCompraID}")
    public ResponseEntity<CreditCompras> updateCompraPontos(@PathVariable Integer creditCompraID, @Valid @RequestBody CreditCompras compraPontosDetails) {
        return creditComprasRepository.findById(creditCompraID)
                .map(compra -> {
                    compra.setCreditID(compraPontosDetails.getCreditID());
                    compra.setCompraID(compraPontosDetails.getCompraID());
                    CreditCompras updatedCompra = creditComprasRepository.save(compra);
                    return ResponseEntity.ok().body(updatedCompra);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Deletar uma compra de pontos
    @DeleteMapping("/{creditCompraID}")
    public ResponseEntity<?> deleteCompraPontos(@PathVariable Integer creditCompraID) {
        return creditComprasRepository.findById(creditCompraID)
                .map(compra -> {
                    creditComprasRepository.delete(compra);
                    return ResponseEntity.ok().build();
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
