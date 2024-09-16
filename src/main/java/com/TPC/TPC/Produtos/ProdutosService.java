package com.TPC.TPC.Produtos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutosService {

    @Autowired
    private ProdutosRepository produtosRepository;

    public Page<Produtos> listarProdutos(String produtos, Pageable pageable) {
        if (produtos != null) {
            return produtosRepository.findByValor(produtos, pageable);
        }
        return produtosRepository.findAll(pageable);
    }

    public ResponseEntity<Produtos> getProdutoById(Integer produtoID) {
        Optional<Produtos> produto = produtosRepository.findById(produtoID);
        return produto.map(ResponseEntity::ok)
                      .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Produtos> createProduto(Produtos produto) {
        Produtos savedProduto = produtosRepository.save(produto);
        return new ResponseEntity<>(savedProduto, HttpStatus.CREATED);
    }

    public ResponseEntity<Produtos> updateProduto(Integer produtoID, Produtos produtoDetails) {
        return produtosRepository.findById(produtoID)
                .map(produto -> {
                    produto.setPdvID(produtoDetails.getPdvID());
                    produto.setCategoriaID(produtoDetails.getCategoriaID());
                    produto.setNome(produtoDetails.getNome());
                    produto.setDescricao(produtoDetails.getDescricao());
                    produto.setValor(produtoDetails.getValor());
                    produto.setAtivo(produtoDetails.getAtivo());
                    Produtos updatedProduto = produtosRepository.save(produto);
                    return ResponseEntity.ok(updatedProduto);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<?> deleteProduto(Integer produtoID) {
        return produtosRepository.findById(produtoID)
                .map(produto -> {
                    produtosRepository.delete(produto);
                    return ResponseEntity.ok().build();
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
