package com.TPC.TPC.controller;

import com.TPC.TPC.model.Credit;
import com.TPC.TPC.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/credits")
public class CreditController {

    @Autowired
    private CreditRepository creditRepository;

    // Buscar todos os créditos
    @GetMapping
    public ResponseEntity<List<Credit>> getAllCredits() {
        List<Credit> credits = creditRepository.findAll();
        return ResponseEntity.ok().body(credits);
    }

    // Buscar um crédito pelo ID
    @GetMapping("/{creditId}")
    public ResponseEntity<Credit> getCreditById(@PathVariable Integer creditId) {
        return creditRepository.findById(creditId)
                .map(credit -> ResponseEntity.ok().body(credit))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Criar um novo crédito
    @PostMapping
    public ResponseEntity<Credit> createCredit(@Valid @RequestBody Credit credit) {
        Credit savedCredit = creditRepository.save(credit);
        return new ResponseEntity<>(savedCredit, HttpStatus.CREATED);
    }

    // Atualizar um crédito existente
    @PutMapping("/{creditId}")
    public ResponseEntity<Credit> updateCredit(@PathVariable Integer creditId, @Valid @RequestBody Credit creditDetails) {
        return creditRepository.findById(creditId)
                .map(credit -> {
                    credit.setCompraId(creditDetails.getCompraId());
                    credit.setValor(creditDetails.getValor());
                    credit.setDataCredito(creditDetails.getDataCredito());
                    credit.setDataExpiracao(creditDetails.getDataExpiracao());
                    credit.setUtilizado(creditDetails.getUtilizado());
                    Credit updatedCredit = creditRepository.save(credit);
                    return ResponseEntity.ok().body(updatedCredit);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Deletar um crédito
    @DeleteMapping("/{creditId}")
    public ResponseEntity<?> deleteCredit(@PathVariable Integer creditId) {
        return creditRepository.findById(creditId)
                .map(credit -> {
                    creditRepository.delete(credit);
                    return ResponseEntity.ok().build();
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
