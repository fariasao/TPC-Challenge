package com.TPC.TPC.controller;

import com.TPC.TPC.model.UserPDV;
import com.TPC.TPC.repository.UserPDVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("userpdv")
@CacheConfig(cacheNames = "userpdv")
public class UserPDVController {

    @Autowired
    private UserPDVRepository userPDVRepository;

    // Buscar todos os usuários PDV
    @GetMapping
    @Cacheable("userpdv")
    public ResponseEntity<List<UserPDV>> getAllUserPDVs() {
        List<UserPDV> userPDVs = userPDVRepository.findAll();
        return ResponseEntity.ok().body(userPDVs);
    }

    // Buscar um usuário PDV pelo ID
    @GetMapping("{userPdvID}")
    public ResponseEntity<UserPDV> getUserPDVById(@PathVariable Integer userPdvID) {
        return userPDVRepository.findById(userPdvID)
                .map(userPDV -> ResponseEntity.ok().body(userPDV))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Criar um novo usuário PDV
    @PostMapping
    @CacheEvict(allEntries = true)
    public ResponseEntity<UserPDV> createUserPDV(@Valid @RequestBody UserPDV userPDV) {
        UserPDV savedUserPDV = userPDVRepository.save(userPDV);
        return new ResponseEntity<>(savedUserPDV, HttpStatus.CREATED);
    }

    // Atualizar um usuário PDV existente
    @PutMapping("{userPdvID}")
    @CacheEvict(allEntries = true)
    public ResponseEntity<UserPDV> updateUserPDV(@PathVariable Integer userPdvID, @Valid @RequestBody UserPDV userPDVDetails) {
        return userPDVRepository.findById(userPdvID)
                .map(userPDV -> {
                    userPDV.setPdvID(userPDVDetails.getPdvID());
                    userPDV.setNome(userPDVDetails.getNome());
                    userPDV.setSobrenome(userPDVDetails.getSobrenome());
                    userPDV.setEmail(userPDVDetails.getEmail());
                    userPDV.setPassword(userPDVDetails.getPassword());
                    userPDV.setDataRegistro(userPDVDetails.getDataRegistro());
                    userPDV.setAtivo(userPDVDetails.isAtivo());
                    UserPDV updatedUserPDV = userPDVRepository.save(userPDV);
                    return ResponseEntity.ok().body(updatedUserPDV);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Deletar um usuário PDV
    @DeleteMapping("{userPdvID}")
    @CacheEvict(allEntries = true)
    public ResponseEntity<?> deleteUserPDV(@PathVariable Integer userPdvID) {
        return userPDVRepository.findById(userPdvID)
                .map(userPDV -> {
                    userPDVRepository.delete(userPDV);
                    return ResponseEntity.ok().build();
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
