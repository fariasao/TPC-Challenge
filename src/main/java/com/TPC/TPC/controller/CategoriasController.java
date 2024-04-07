package com.TPC.TPC.controller;

import com.TPC.TPC.model.Categorias;
import com.TPC.TPC.repository.CategoriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriasController {

    @Autowired
    private CategoriasRepository categoriasRepository;

    // Buscar todas as categorias
    @GetMapping
    public List<Categorias> getAllCategorias() {
        return categoriasRepository.findAll();
    }

    // Buscar uma categoria pelo ID
    @GetMapping("/{categoriaId}")
    public ResponseEntity<Categorias> getCategoriaById(@PathVariable Integer categoriaId) {
        return categoriasRepository.findById(categoriaId)
                .map(categoria -> ResponseEntity.ok(categoria))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Criar uma nova categoria
    @PostMapping
    public ResponseEntity<Categorias> createCategoria(@Valid @RequestBody Categorias categoria) {
        Categorias savedCategoria = categoriasRepository.save(categoria);
        return new ResponseEntity<>(savedCategoria, HttpStatus.CREATED);
    }

    // Atualizar uma categoria existente
    @PutMapping("/{categoriaId}")
    public ResponseEntity<Categorias> updateCategoria(@PathVariable Integer categoriaId, @Valid @RequestBody Categorias categoriaDetails) {
        return categoriasRepository.findById(categoriaId)
                .map(categoria -> {
                    categoria.setNome(categoriaDetails.getNome());
                    categoria.setDescricao(categoriaDetails.getDescricao());
                    categoria.setAtivo(categoriaDetails.getAtivo());
                    final Categorias updatedCategoria = categoriasRepository.save(categoria);
                    return ResponseEntity.ok(updatedCategoria);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Deletar uma categoria
    @DeleteMapping("/{categoriaId}")
    public ResponseEntity<?> deleteCategoria(@PathVariable Integer categoriaId) {
        return categoriasRepository.findById(categoriaId)
                .map(categoria -> {
                    categoriasRepository.delete(categoria);
                    return ResponseEntity.ok().build();
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}