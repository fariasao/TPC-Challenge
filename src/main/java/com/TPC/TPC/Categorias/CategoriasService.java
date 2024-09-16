package com.TPC.TPC.Categorias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoriasService {
    @Autowired
    private CategoriasRepository categoriasRepository;

    public Page<Categorias> listarCategorias(String categorias, Pageable pageable) {
        if (categorias != null) {
            return categoriasRepository.findByNome(categorias, pageable);
        }
        return categoriasRepository.findAll(pageable);
    }

    public ResponseEntity<Categorias> getCategoriaById(Integer categoriaID) {
        return categoriasRepository.findById(categoriaID)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Categorias> createCategoria(Categorias categorias) {
        Categorias savedCategorias = categoriasRepository.save(categorias);
        return new ResponseEntity<>(savedCategorias, HttpStatus.CREATED);
    }

    public ResponseEntity<Categorias> updateCategorias(Integer categoriaID, Categorias categoriasDetails) {
        return categoriasRepository.findById(categoriaID)
                .map(categorias -> {
                    categorias.setNome(categoriasDetails.getNome());
                    categorias.setDescricao(categoriasDetails.getDescricao());
                    categorias.setAtivo(categoriasDetails.getAtivo());
                    Categorias updatedCategorias = categoriasRepository.save(categorias);
                    return ResponseEntity.ok(updatedCategorias);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<?> deleteCategoria(Integer categoriaID) {
        return categoriasRepository.findById(categoriaID)
                .map(categorias -> {
                    categoriasRepository.delete(categorias);
                    return ResponseEntity.ok().build();
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
