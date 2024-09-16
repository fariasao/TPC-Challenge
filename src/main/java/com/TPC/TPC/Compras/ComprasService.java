package com.TPC.TPC.Compras;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ComprasService {
    @Autowired
    private ComprasRepository comprasRepository;

    public Page<Compras> listarCompras(String compra, Pageable pageable) {
        if (compra != null) {
            return comprasRepository.findByDataCompra(compra, pageable);
        }
        return comprasRepository.findAll(pageable);
    }

    public ResponseEntity<Compras> getCompraById(Integer compraID) {
        return comprasRepository.findById(compraID)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Compras> createCompra(Compras compra) {
        Compras savedCompras = comprasRepository.save(compra);
        return new ResponseEntity<>(savedCompras, HttpStatus.CREATED);
    }

    public ResponseEntity<Compras> updateCompra(Integer compraID, Compras comprasDetails) {
        return comprasRepository.findById(compraID)
                .map(compra -> {
                    compra.setUsersID(comprasDetails.getUsersID());
                    compra.setPdvID(comprasDetails.getPdvID());
                    compra.setValor(comprasDetails.getValor());
                    compra.setDataCompra(comprasDetails.getDataCompra());
                    Compras updatedCompras = comprasRepository.save(compra);
                    return ResponseEntity.ok(updatedCompras);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<?> deleteCompra(Integer compraID) {
        return comprasRepository.findById(compraID)
                .map(compra -> {
                    comprasRepository.delete(compra);
                    return ResponseEntity.ok().build();
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
