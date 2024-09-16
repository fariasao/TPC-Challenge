package com.TPC.TPC.Campanhas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CampanhasService {
    @Autowired
    private CampanhasRepository campanhasRepository;

    public Page<Campanhas> listarCampanhas(String campanha, Pageable pageable) {
        if (campanha != null) {
            return campanhasRepository.findByTitulo(campanha, pageable);
        }
        return campanhasRepository.findAll(pageable);
    }

    public ResponseEntity<Campanhas> getCampanhaById(Integer campanhaID) {
        return campanhasRepository.findById(campanhaID)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Campanhas> createCampanha(Campanhas campanha) {
        Campanhas savedCampanha = campanhasRepository.save(campanha);
        return new ResponseEntity<>(savedCampanha, HttpStatus.CREATED);
    }

    public ResponseEntity<Campanhas> updateCampanha(Integer campanhaID, Campanhas campanhaDetails) {
        return campanhasRepository.findById(campanhaID)
                .map(campanha -> {
                    campanha.setMasterID(campanhaDetails.getMasterID());
                    campanha.setClusterID(campanhaDetails.getClusterID());
                    campanha.setTitulo(campanhaDetails.getTitulo());
                    campanha.setConteudo(campanhaDetails.getConteudo());
                    campanha.setDescricao(campanhaDetails.getDescricao());
                    campanha.setCanalTipo(campanhaDetails.getCanalTipo());
                    Campanhas updatedCampanha = campanhasRepository.save(campanha);
                    return ResponseEntity.ok(updatedCampanha);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<?> deletePontos(Integer campanhaID) {
        return campanhasRepository.findById(campanhaID)
                .map(campanha -> {
                    campanhasRepository.delete(campanha);
                    return ResponseEntity.ok().build();
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
