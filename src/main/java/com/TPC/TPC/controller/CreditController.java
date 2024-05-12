package com.TPC.TPC.controller;

import com.TPC.TPC.model.Credit;
import com.TPC.TPC.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("credits")
@CacheConfig(cacheNames = "credits")
public class CreditController {

    @Autowired
    private CreditRepository creditRepository;

    // Buscar todos os créditos
    @GetMapping
    @Cacheable("credits")
    public Page<Credit> listarCreditos(
        @RequestParam(required = false) String credit,
        @PageableDefault(sort = "dataCredito", direction = Direction.ASC  ) Pageable pageable
    ) {
        if (credit != null){
            return creditRepository.findByDataCredito(credit, pageable);
        }
        return creditRepository.findAll(pageable);
    }

    // Buscar um crédito pelo ID
    @GetMapping("{creditID}")
    public ResponseEntity<Credit> getCreditById(@PathVariable Integer creditID) {
        return creditRepository.findById(creditID)
                .map(credit -> ResponseEntity.ok().body(credit))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Criar um novo crédito
    @PostMapping
    @CacheEvict(allEntries = true)
    public ResponseEntity<Credit> createCredit(@Valid @RequestBody Credit credit) {
        Credit savedCredit = creditRepository.save(credit);
        return new ResponseEntity<>(savedCredit, HttpStatus.CREATED);
    }

    // Atualizar um crédito existente
    @PutMapping("{creditID}")
    @CacheEvict(allEntries = true)
    public ResponseEntity<Credit> updateCredit(@PathVariable Integer creditID, @Valid @RequestBody Credit creditDetails) {
        return creditRepository.findById(creditID)
                .map(credit -> {
                    credit.setValor(creditDetails.getValor());
                    credit.setDataCredito(creditDetails.getDataCredito());
                    credit.setDataExpiracao(creditDetails.getDataExpiracao());
                    credit.setUtilizado(creditDetails.isUtilizado());
                    Credit updatedCredit = creditRepository.save(credit);
                    return ResponseEntity.ok().body(updatedCredit);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Deletar um crédito
    @DeleteMapping("{creditID}")
    @CacheEvict(allEntries = true)
    public ResponseEntity<?> deleteCredit(@PathVariable Integer creditID) {
        return creditRepository.findById(creditID)
                .map(credit -> {
                    creditRepository.delete(credit);
                    return ResponseEntity.ok().build();
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
