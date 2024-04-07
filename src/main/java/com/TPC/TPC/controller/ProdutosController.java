package com.TPC.TPC.controller;

import com.TPC.TPC.model.Produtos;
import com.TPC.TPC.repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

    @Autowired
    private ProdutosRepository produtosRepository;

    // Buscar todos os produtos
    @GetMapping
    public ResponseEntity<List<Produtos>> getAllProdutos() {
        List<Produtos> produtos = produtosRepository.findAll();
        return ResponseEntity.ok().body(produtos);
    }

    // Buscar um produto pelo ID
    @GetMapping("/{pdvId}")
    public ResponseEntity<Produtos> getProdutoById(@PathVariable Integer pdvId) {
        return produtosRepository.findById(pdvId)
                .map(produto -> ResponseEntity.ok().body(produto))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Criar um novo produto
    @PostMapping
    public ResponseEntity<Produtos> createProduto(@Valid @RequestBody Produtos produto) {
        Produtos savedProduto = produtosRepository.save(produto);
        return new ResponseEntity<>(savedProduto, HttpStatus.CREATED);
    }

    // Atualizar um produto existente
    @PutMapping("/{pdvId}")
    public ResponseEntity<Produtos> updateProduto(@PathVariable Integer pdvId, @Valid @RequestBody Produtos produtoDetails) {
        return produtosRepository.findById(pdvId)
                .map(produto -> {
                    produto.setCategoriaId(produtoDetails.getCategoriaId());
                    produto.setNome(produtoDetails.getNome());
                    produto.setDescricao(produtoDetails.getDescricao());
                    produto.setValor(produtoDetails.getValor());
                    produto.setAtivo(produtoDetails.getAtivo());
                    produto.setCompraPontosPedidoId(produtoDetails.getCompraPontosPedidoId());
                    Produtos updatedProduto = produtosRepository.save(produto);
                    return ResponseEntity.ok().body(updatedProduto);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Deletar um produto
    @DeleteMapping("/{pdvId}")
    public ResponseEntity<?> deleteProduto(@PathVariable Integer pdvId) {
        return produtosRepository.findById(pdvId)
                .map(produto -> {
                    produtosRepository.delete(produto);
                    return ResponseEntity.ok().build();
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
