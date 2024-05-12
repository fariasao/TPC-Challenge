package com.TPC.TPC.controller;

import com.TPC.TPC.model.Compras;
import com.TPC.TPC.repository.ComprasRepository;
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
@RequestMapping("compras")
@CacheConfig(cacheNames = "compras")
public class ComprasController {

    @Autowired
    private ComprasRepository comprasRepository;

    // Buscar todas as compras
    @GetMapping
    @Cacheable("compras")
    public Page<Compras> listarCompras(
        @RequestParam(required = false) String compras,
        @PageableDefault(sort = "dataCompra", direction = Direction.DESC  ) Pageable pageable
    ) {
        if (compras != null){
            return comprasRepository.findByDataCompra(compras, pageable);
        }
        return comprasRepository.findAll(pageable);
    }

    // Buscar uma compra pelo ID
    @GetMapping("{compraID}")
    public ResponseEntity<Compras> getCompraById(@PathVariable Integer compraID) {
        return comprasRepository.findById(compraID)
                .map(compra -> ResponseEntity.ok().body(compra))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Criar uma nova compra
    @PostMapping
    @CacheEvict(allEntries = true)
    public ResponseEntity<Compras> createCompra(@Valid @RequestBody Compras compra) {
        Compras savedCompra = comprasRepository.save(compra);
        return new ResponseEntity<>(savedCompra, HttpStatus.CREATED);
    }

    // Atualizar uma compra existente
    @PutMapping("{compraID}")
    @CacheEvict(allEntries = true)
    public ResponseEntity<Compras> updateCompra(@PathVariable Integer compraID, @Valid @RequestBody Compras compraDetails) {
        return comprasRepository.findById(compraID)
                .map(compra -> {
                    compra.setUsersID(compraDetails.getUsersID());
                    compra.setPdvID(compraDetails.getPdvID());
                    compra.setValor(compraDetails.getValor());
                    compra.setDataCompra(compraDetails.getDataCompra());
                    Compras updatedCompra = comprasRepository.save(compra);
                    return ResponseEntity.ok().body(updatedCompra);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Deletar uma compra
    @DeleteMapping("{compraID}")
    @CacheEvict(allEntries = true)
    public ResponseEntity<?> deleteCompra(@PathVariable Integer compraID) {
        return comprasRepository.findById(compraID)
                .map(compra -> {
                    comprasRepository.delete(compra);
                    return ResponseEntity.ok().build();
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
