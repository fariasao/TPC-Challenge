package com.TPC.TPC.controller;

import com.TPC.TPC.model.UserCluster;
import com.TPC.TPC.repository.UserClusterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usercluster")
public class UserClusterController {

    @Autowired
    private UserClusterRepository userClusterRepository;

    // Buscar todas as associações UserCluster
    @GetMapping
    public ResponseEntity<List<UserCluster>> getAllUserClusters() {
        List<UserCluster> userClusters = userClusterRepository.findAll();
        return ResponseEntity.ok().body(userClusters);
    }

    // Buscar uma associação UserCluster pelo ID
    @GetMapping("/{clusterInfoId}")
    public ResponseEntity<UserCluster> getUserClusterById(@PathVariable Integer clusterInfoId) {
        return userClusterRepository.findById(clusterInfoId)
                .map(userCluster -> ResponseEntity.ok().body(userCluster))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Criar uma nova associação UserCluster
    @PostMapping
    public ResponseEntity<UserCluster> createUserCluster(@Valid @RequestBody UserCluster userCluster) {
        UserCluster savedUserCluster = userClusterRepository.save(userCluster);
        return new ResponseEntity<>(savedUserCluster, HttpStatus.CREATED);
    }

    // Atualizar uma associação UserCluster existente
    @PutMapping("/{clusterInfoId}")
    public ResponseEntity<UserCluster> updateUserCluster(@PathVariable Integer clusterInfoId, @Valid @RequestBody UserCluster userClusterDetails) {
        return userClusterRepository.findById(clusterInfoId)
                .map(userCluster -> {
                    userCluster.setClusterId(userClusterDetails.getClusterId());
                    userCluster.setUserId(userClusterDetails.getUserId());
                    UserCluster updatedUserCluster = userClusterRepository.save(userCluster);
                    return ResponseEntity.ok().body(updatedUserCluster);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Deletar uma associação UserCluster
    @DeleteMapping("/{clusterInfoId}")
    public ResponseEntity<?> deleteUserCluster(@PathVariable Integer clusterInfoId) {
        return userClusterRepository.findById(clusterInfoId)
                .map(userCluster -> {
                    userClusterRepository.delete(userCluster);
                    return ResponseEntity.ok().build();
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
