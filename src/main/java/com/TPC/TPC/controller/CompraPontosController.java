package com.TPC.TPC.controller;

import com.TPC.TPC.model.CompraPontos;
import com.TPC.TPC.repository.CompraPontosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/comprapontos")
public class CompraPontosController {

    @Autowired
    private CompraPontosRepository compraPontosRepository;

    // Buscar todas as compras de pontos
    @GetMapping
    public ResponseEntity<List<CompraPontos>> getAllCompraPontos() {
        List<CompraPontos> compras = compraPontosRepository.findAll();
        return ResponseEntity.ok().body(compras);
    }

    // Buscar uma compra de pontos pelo ID do pedido
    @GetMapping("/{pedidoId}")
    public ResponseEntity<CompraPontos> getCompraPontosById(@PathVariable Integer pedidoId) {
        return compraPontosRepository.findById(pedidoId)
                .map(compra -> ResponseEntity.ok().body(compra))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Criar uma nova compra de pontos
    @PostMapping
    public ResponseEntity<CompraPontos> createCompraPontos(@Valid @RequestBody CompraPontos compraPontos) {
        CompraPontos savedCompra = compraPontosRepository.save(compraPontos);
        return new ResponseEntity<>(savedCompra, HttpStatus.CREATED);
    }

    // Atualizar uma compra de pontos existente
    @PutMapping("/{pedidoId}")
    public ResponseEntity<CompraPontos> updateCompraPontos(@PathVariable Integer pedidoId, @Valid @RequestBody CompraPontos compraPontosDetails) {
        return compraPontosRepository.findById(pedidoId)
                .map(compra -> {
                    compra.setLoja(compraPontosDetails.getLoja());
                    compra.setItemId(compraPontosDetails.getItemId());
                    compra.setQuantidade(compraPontosDetails.getQuantidade());
                    CompraPontos updatedCompra = compraPontosRepository.save(compra);
                    return ResponseEntity.ok().body(updatedCompra);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Deletar uma compra de pontos
    @DeleteMapping("/{pedidoId}")
    public ResponseEntity<?> deleteCompraPontos(@PathVariable Integer pedidoId) {
        return compraPontosRepository.findById(pedidoId)
                .map(compra -> {
                    compraPontosRepository.delete(compra);
                    return ResponseEntity.ok().build();
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
