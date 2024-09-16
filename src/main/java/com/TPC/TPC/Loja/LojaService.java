package com.TPC.TPC.Loja;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class LojaService {
    @Autowired
    private LojaRepository lojaRepository;

    public Page<Loja> listarLojas(String loja, Pageable pageable) {
        if (loja != null) {
            return lojaRepository.findByNomeLoja(loja, pageable);
        }
        return lojaRepository.findAll(pageable);
    }

    public ResponseEntity<Loja> getLojasById(Integer pdvID) {
        return lojaRepository.findById(pdvID)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Loja> createLoja(Loja loja) {
        Loja savedLoja = lojaRepository.save(loja);
        return new ResponseEntity<>(savedLoja, HttpStatus.CREATED);
    }

    public ResponseEntity<Loja> updateLoja(Integer pdvID, Loja lojaDetails) {
        return lojaRepository.findById(pdvID)
                .map(loja -> {
                    loja.setNomeLoja(lojaDetails.getNomeLoja());
                    loja.setEndereco(lojaDetails.getEndereco());
                    loja.setNumero(lojaDetails.getNumero());
                    loja.setComplemento(lojaDetails.getComplemento());
                    loja.setCep(lojaDetails.getCep());
                    loja.setActive(lojaDetails.getActive());
                    Loja updatedLoja = lojaRepository.save(loja);
                    return ResponseEntity.ok(updatedLoja);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<?> deleteLoja(Integer pdvID) {
        return lojaRepository.findById(pdvID)
                .map(loja -> {
                    lojaRepository.delete(loja);
                    return ResponseEntity.ok().build();
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
