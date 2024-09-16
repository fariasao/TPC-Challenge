package com.TPC.TPC.CreditCompras;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreditComprasService {
    @Autowired
    private CreditComprasRepository creditComprasRepository;

    public Page<CreditCompras> listarCreditCompras(String creditCompra, Pageable pageable) {
        if (creditCompra != null) {
            return creditComprasRepository.findByCreditCompraID(creditCompra, pageable);
        }
        return creditComprasRepository.findAll(pageable);
    }

    public ResponseEntity<CreditCompras> getCreditComprasById(Integer creditCompraID) {
        return creditComprasRepository.findById(creditCompraID)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<CreditCompras> createCreditCompras(CreditCompras creditCompra) {
        CreditCompras savedCreditCompras = creditComprasRepository.save(creditCompra);
        return new ResponseEntity<>(savedCreditCompras, HttpStatus.CREATED);
    }

    public ResponseEntity<CreditCompras> updateCreditCompras(Integer creditCompraID, CreditCompras creditComprasDetails) {
        return creditComprasRepository.findById(creditCompraID)
                .map(creditCompra -> {
                    creditCompra.setCreditID(creditComprasDetails.getCreditID());
                    creditCompra.setCompraID(creditComprasDetails.getCompraID());
                    CreditCompras updatedCreditCompras = creditComprasRepository.save(creditCompra);
                    return ResponseEntity.ok(updatedCreditCompras);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<?> deleteCreditCompras(Integer creditCompraID) {
        return creditComprasRepository.findById(creditCompraID)
                .map(creditCompra -> {
                    creditComprasRepository.delete(creditCompra);
                    return ResponseEntity.ok().build();
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
