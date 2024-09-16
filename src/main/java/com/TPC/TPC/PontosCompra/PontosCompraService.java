package com.TPC.TPC.PontosCompra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PontosCompraService {

    @Autowired
    private PontosCompraRepository pontosComprasRepository;

    public Page<PontosCompra> listarPontosCompra(String pontosCompra, Pageable pageable) {
        if (pontosCompra != null) {
            return pontosComprasRepository.findByPontosCompraID(pontosCompra, pageable);
        }
        return pontosComprasRepository.findAll(pageable);
    }

    public ResponseEntity<PontosCompra> getCompraPontosById(Integer pontosCompraID) {
        return pontosComprasRepository.findById(pontosCompraID)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<PontosCompra> createCompraPontos(PontosCompra pontosCompra) {
        PontosCompra savedCompra = pontosComprasRepository.save(pontosCompra);
        return new ResponseEntity<>(savedCompra, HttpStatus.CREATED);
    }

    public ResponseEntity<PontosCompra> updateCompraPontos(Integer pontosCompraID, PontosCompra pontosCompraDetails) {
        return pontosComprasRepository.findById(pontosCompraID)
                .map(compra -> {
                    compra.setCompraID(pontosCompraDetails.getCompraID());
                    compra.setPointID(pontosCompraDetails.getPointID());
                    PontosCompra updatedCompra = pontosComprasRepository.save(compra);
                    return ResponseEntity.ok(updatedCompra);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<?> deleteCompraPontos(Integer pontosCompraID) {
        return pontosComprasRepository.findById(pontosCompraID)
                .map(compra -> {
                    pontosComprasRepository.delete(compra);
                    return ResponseEntity.ok().build();
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
