package com.TPC.TPC.Credit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreditService {
    @Autowired
    private CreditRepository creditRepository;

    public Page<Credit> listarCreditos(String credit, Pageable pageable) {
        if (credit != null) {
            return creditRepository.findByDataCredito(credit, pageable);
        }
        return creditRepository.findAll(pageable);
    }

    public ResponseEntity<Credit> getCreditosById(Integer creditID) {
        return creditRepository.findById(creditID)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Credit> createCreditos(Credit credit) {
        Credit savedPontos = creditRepository.save(credit);
        return new ResponseEntity<>(savedPontos, HttpStatus.CREATED);
    }

    public ResponseEntity<Credit> updateCreditos(Integer creditID, Credit creditosDetails) {
        return creditRepository.findById(creditID)
                .map(credit -> {
                    credit.setValor(creditosDetails.getValor());
                    credit.setDataCredito(creditosDetails.getDataCredito());
                    credit.setDataExpiracao(creditosDetails.getDataExpiracao());
                    credit.setUtilizado(creditosDetails.getUtilizado());
                    Credit updatedCreditos = creditRepository.save(credit);
                    return ResponseEntity.ok(updatedCreditos);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<?> deleteCreditos(Integer creditID) {
        return creditRepository.findById(creditID)
                .map(credit -> {
                    creditRepository.delete(credit);
                    return ResponseEntity.ok().build();
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
