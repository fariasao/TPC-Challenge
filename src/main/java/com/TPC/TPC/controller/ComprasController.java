package com.TPC.TPC.controller;

import com.TPC.TPC.model.Compras;
import com.TPC.TPC.repository.ComprasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/compras")
public class ComprasController {

    @Autowired
    private ComprasRepository comprasRepository;

    // Buscar todas as compras
    @GetMapping
    public ResponseEntity<List<Compras>> getAllCompras() {
        List<Compras> compras = comprasRepository.findAll();
        return ResponseEntity.ok().body(compras);
    }

    // Buscar uma compra pelo ID
    @GetMapping("/{compraId}")
    public ResponseEntity<Compras> getCompraById(@PathVariable Integer compraId) {
        return comprasRepository.findById(compraId)
                .map(compra -> ResponseEntity.ok().body(compra))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Criar uma nova compra
    @PostMapping
    public ResponseEntity<Compras> createCompra(@Valid @RequestBody Compras compra) {
        Compras savedCompra = comprasRepository.save(compra);
        return new ResponseEntity<>(savedCompra, HttpStatus.CREATED);
    }

    // Atualizar uma compra existente
    @PutMapping("/{compraId}")
    public ResponseEntity<Compras> updateCompra(@PathVariable Integer compraId, @Valid @RequestBody Compras compraDetails) {
        return comprasRepository.findById(compraId)
                .map(compra -> {
                    compra.setUser(compraDetails.getUser());
                    compra.setLoja(compraDetails.getLoja());
                    compra.setValor(compraDetails.getValor());
                    compra.setDataCompra(compraDetails.getDataCompra());
                    Compras updatedCompra = comprasRepository.save(compra);
                    return ResponseEntity.ok().body(updatedCompra);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Deletar uma compra
    @DeleteMapping("/{compraId}")
    public ResponseEntity<?> deleteCompra(@PathVariable Integer compraId) {
        return comprasRepository.findById(compraId)
                .map(compra -> {
                    comprasRepository.delete(compra);
                    return ResponseEntity.ok().build();
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
