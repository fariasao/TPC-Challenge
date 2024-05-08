package com.TPC.TPC.controller;

import com.TPC.TPC.model.UserMaster;
import com.TPC.TPC.repository.UserMasterRepository;
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
@RequestMapping("usermasters")
@CacheConfig(cacheNames = "usermasters")
public class UserMasterController {

    @Autowired
    private UserMasterRepository userMasterRepository;

    // Buscar todos os usuários mestres
    @GetMapping
    @Cacheable("usermasters")
    public ResponseEntity<List<UserMaster>> getAllUserMasters() {
        List<UserMaster> userMasters = userMasterRepository.findAll();
        return ResponseEntity.ok().body(userMasters);
    }

    // Buscar um usuário mestre pelo ID
    @GetMapping("{masterID}")
    public ResponseEntity<UserMaster> getUserMasterById(@PathVariable Integer masterID) {
        return userMasterRepository.findById(masterID)
                .map(userMaster -> ResponseEntity.ok().body(userMaster))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Criar um novo usuário mestre
    @PostMapping
    @CacheEvict(allEntries = true)
    public ResponseEntity<UserMaster> createUserMaster(@Valid @RequestBody UserMaster userMaster) {
        UserMaster savedUserMaster = userMasterRepository.save(userMaster);
        return new ResponseEntity<>(savedUserMaster, HttpStatus.CREATED);
    }

    // Atualizar um usuário mestre existente
    @PutMapping("{masterID}")
    @CacheEvict(allEntries = true)
    public ResponseEntity<UserMaster> updateUserMaster(@PathVariable Integer masterID, @Valid @RequestBody UserMaster userMasterDetails) {
        return userMasterRepository.findById(masterID)
                .map(userMaster -> {
                    userMaster.setNome(userMasterDetails.getNome());
                    userMaster.setSobrenome(userMasterDetails.getSobrenome());
                    userMaster.setEmail(userMasterDetails.getEmail());
                    userMaster.setPassword(userMasterDetails.getPassword());
                    userMaster.setDataRegistro(userMasterDetails.getDataRegistro());
                    userMaster.setAtivo(userMasterDetails.isAtivo());
                    UserMaster updatedUserMaster = userMasterRepository.save(userMaster);
                    return ResponseEntity.ok().body(updatedUserMaster);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Deletar um usuário mestre
    @DeleteMapping("{masterID}")
    @CacheEvict(allEntries = true)
    public ResponseEntity<?> deleteUserMaster(@PathVariable Integer masterID) {
        return userMasterRepository.findById(masterID)
                .map(userMaster -> {
                    userMasterRepository.delete(userMaster);
                    return ResponseEntity.ok().build();
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
