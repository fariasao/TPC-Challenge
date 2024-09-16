package com.TPC.TPC.Pontos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PontosService {

    @Autowired
    private PontosRepository pontosRepository;

    public Page<Pontos> listarPontos(String pontos, Pageable pageable) {
        if (pontos != null) {
            return pontosRepository.findByDataCredito(pontos, pageable);
        }
        return pontosRepository.findAll(pageable);
    }

    public ResponseEntity<Pontos> getPontosById(Integer pointID) {
        return pontosRepository.findById(pointID)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Pontos> createPontos(Pontos ponto) {
        Pontos savedPontos = pontosRepository.save(ponto);
        return new ResponseEntity<>(savedPontos, HttpStatus.CREATED);
    }

    public ResponseEntity<Pontos> updatePontos(Integer pointID, Pontos pontosDetails) {
        return pontosRepository.findById(pointID)
                .map(ponto -> {
                    ponto.setValor(pontosDetails.getValor());
                    ponto.setDataCredito(pontosDetails.getDataCredito());
                    ponto.setDataExpiracao(pontosDetails.getDataExpiracao());
                    ponto.setUtilizado(pontosDetails.getUtilizado());
                    Pontos updatedPontos = pontosRepository.save(ponto);
                    return ResponseEntity.ok(updatedPontos);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<?> deletePontos(Integer pointID) {
        return pontosRepository.findById(pointID)
                .map(ponto -> {
                    pontosRepository.delete(ponto);
                    return ResponseEntity.ok().build();
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
