package com.TPC.TPC.controller;

import com.TPC.TPC.model.Produtos;
import com.TPC.TPC.repository.ProdutosRepository;
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
@RequestMapping("produtos")
@CacheConfig(cacheNames = "produtos")
public class ProdutosController {

    @Autowired
    private ProdutosRepository produtosRepository;

    // Buscar todos os produtos
    @GetMapping
    @Cacheable("produtos")
    public ResponseEntity<List<Produtos>> getAllProdutos() {
        List<Produtos> produtos = produtosRepository.findAll();
        return ResponseEntity.ok().body(produtos);
    }

    // Buscar um produto pelo ID
    @GetMapping("{produtoID}")
    public ResponseEntity<Produtos> getProdutoById(@PathVariable Integer produtoID) {
        return produtosRepository.findById(produtoID)
                .map(produto -> ResponseEntity.ok().body(produto))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Criar um novo produto
    @PostMapping
    @CacheEvict(allEntries = true)
    public ResponseEntity<Produtos> createProduto(@Valid @RequestBody Produtos produto) {
        Produtos savedProduto = produtosRepository.save(produto);
        return new ResponseEntity<>(savedProduto, HttpStatus.CREATED);
    }

    // Atualizar um produto existente
    @PutMapping("{produtoID}")
    @CacheEvict(allEntries = true)
    public ResponseEntity<Produtos> updateProduto(@PathVariable Integer produtoID, @Valid @RequestBody Produtos produtoDetails) {
        return produtosRepository.findById(produtoID)
                .map(produto -> {
                    produto.setPdvID(produtoDetails.getPdvID());
                    produto.setCategoriaID(produtoDetails.getCategoriaID());
                    produto.setNome(produtoDetails.getNome());
                    produto.setDescricao(produtoDetails.getDescricao());
                    produto.setValor(produtoDetails.getValor());
                    produto.setAtivo(produtoDetails.isAtivo());
                    Produtos updatedProduto = produtosRepository.save(produto);
                    return ResponseEntity.ok().body(updatedProduto);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Deletar um produto
    @DeleteMapping("{produtoID}")
    @CacheEvict(allEntries = true)
    public ResponseEntity<?> deleteProduto(@PathVariable Integer produtoID) {
        return produtosRepository.findById(produtoID)
                .map(produto -> {
                    produtosRepository.delete(produto);
                    return ResponseEntity.ok().build();
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
