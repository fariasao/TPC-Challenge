package com.TPC.TPC.controller;

import com.TPC.TPC.model.UserCluster;
import com.TPC.TPC.repository.UserClusterRepository;
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
@RequestMapping("usercluster")
@CacheConfig(cacheNames = "usercluster")
public class UserClusterController {

    @Autowired
    private UserClusterRepository userClusterRepository;

    // Buscar todas as associações UserCluster
    @GetMapping
    @Cacheable("usercluster")
    public Page<UserCluster> listarUserClusters(
        @RequestParam(required = false) String userCluster,
        @PageableDefault(sort = "userClusterID", direction = Direction.ASC  ) Pageable pageable
    ) {
        if (userCluster != null){
            return userClusterRepository.findByUserClusterID(userCluster, pageable);
        }
        return userClusterRepository.findAll(pageable);
    }

    // Buscar uma associação UserCluster pelo ID
    @GetMapping("{userClusterID}")
    public ResponseEntity<UserCluster> getUserClusterById(@PathVariable Integer userClusterID) {
        return userClusterRepository.findById(userClusterID)
                .map(userCluster -> ResponseEntity.ok().body(userCluster))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Criar uma nova associação UserCluster
    @PostMapping
    @CacheEvict(allEntries = true)
    public ResponseEntity<UserCluster> createUserCluster(@Valid @RequestBody UserCluster userCluster) {
        UserCluster savedUserCluster = userClusterRepository.save(userCluster);
        return new ResponseEntity<>(savedUserCluster, HttpStatus.CREATED);
    }

    // Atualizar uma associação UserCluster existente
    @PutMapping("{userClusterID}")
    @CacheEvict(allEntries = true)
    public ResponseEntity<UserCluster> updateUserCluster(@PathVariable Integer userClusterID, @Valid @RequestBody UserCluster userClusterDetails) {
        return userClusterRepository.findById(userClusterID)
                .map(userCluster -> {
                    userCluster.setClusterID(userClusterDetails.getClusterID());
                    userCluster.setUsersID(userClusterDetails.getUsersID());
                    UserCluster updatedUserCluster = userClusterRepository.save(userCluster);
                    return ResponseEntity.ok().body(updatedUserCluster);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Deletar uma associação UserCluster
    @DeleteMapping("{userClusterID}")
    @CacheEvict(allEntries = true)
    public ResponseEntity<?> deleteUserCluster(@PathVariable Integer userClusterID) {
        return userClusterRepository.findById(userClusterID)
                .map(userCluster -> {
                    userClusterRepository.delete(userCluster);
                    return ResponseEntity.ok().build();
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
