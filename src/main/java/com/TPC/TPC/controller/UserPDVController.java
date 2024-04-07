package com.TPC.TPC.controller;

import com.TPC.TPC.model.UserPDV;
import com.TPC.TPC.repository.UserPDVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/userpdv")
public class UserPDVController {

    @Autowired
    private UserPDVRepository userPDVRepository;

    // Buscar todos os usuários PDV
    @GetMapping
    public ResponseEntity<List<UserPDV>> getAllUserPDVs() {
        List<UserPDV> userPDVs = userPDVRepository.findAll();
        return ResponseEntity.ok().body(userPDVs);
    }

    // Buscar um usuário PDV pelo ID
    @GetMapping("/{atUserId}")
    public ResponseEntity<UserPDV> getUserPDVById(@PathVariable Integer atUserId) {
        return userPDVRepository.findById(atUserId)
                .map(userPDV -> ResponseEntity.ok().body(userPDV))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Criar um novo usuário PDV
    @PostMapping
    public ResponseEntity<UserPDV> createUserPDV(@Valid @RequestBody UserPDV userPDV) {
        UserPDV savedUserPDV = userPDVRepository.save(userPDV);
        return new ResponseEntity<>(savedUserPDV, HttpStatus.CREATED);
    }

    // Atualizar um usuário PDV existente
    @PutMapping("/{atUserId}")
    public ResponseEntity<UserPDV> updateUserPDV(@PathVariable Integer atUserId, @Valid @RequestBody UserPDV userPDVDetails) {
        return userPDVRepository.findById(atUserId)
                .map(userPDV -> {
                    userPDV.setPdvId(userPDVDetails.getPdvId());
                    userPDV.setNome(userPDVDetails.getNome());
                    userPDV.setSobrenome(userPDVDetails.getSobrenome());
                    userPDV.setEmail(userPDVDetails.getEmail());
                    userPDV.setPassword(userPDVDetails.getPassword());
                    userPDV.setDataRegistro(userPDVDetails.getDataRegistro());
                    userPDV.setAtivo(userPDVDetails.getAtivo());
                    UserPDV updatedUserPDV = userPDVRepository.save(userPDV);
                    return ResponseEntity.ok().body(updatedUserPDV);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Deletar um usuário PDV
    @DeleteMapping("/{atUserId}")
    public ResponseEntity<?> deleteUserPDV(@PathVariable Integer atUserId) {
        return userPDVRepository.findById(atUserId)
                .map(userPDV -> {
                    userPDVRepository.delete(userPDV);
                    return ResponseEntity.ok().build();
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
