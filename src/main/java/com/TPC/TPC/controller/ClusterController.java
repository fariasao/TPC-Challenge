package com.TPC.TPC.controller;

import com.TPC.TPC.model.Cluster;
import com.TPC.TPC.repository.ClusterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clusters")
public class ClusterController {

    @Autowired
    private ClusterRepository clusterRepository;

    // Buscar todos os clusters
    @GetMapping
    public ResponseEntity<List<Cluster>> getAllClusters() {
        List<Cluster> clusters = clusterRepository.findAll();
        return ResponseEntity.ok().body(clusters);
    }

    // Buscar um cluster pelo ID
    @GetMapping("/{clusterId}")
    public ResponseEntity<Cluster> getClusterById(@PathVariable Integer clusterId) {
        return clusterRepository.findById(clusterId)
                .map(cluster -> ResponseEntity.ok().body(cluster))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Criar um novo cluster
    @PostMapping
    public ResponseEntity<Cluster> createCluster(@Valid @RequestBody Cluster cluster) {
        Cluster savedCluster = clusterRepository.save(cluster);
        return new ResponseEntity<>(savedCluster, HttpStatus.CREATED);
    }

    // Atualizar um cluster existente
    @PutMapping("/{clusterId}")
    public ResponseEntity<Cluster> updateCluster(@PathVariable Integer clusterId, @Valid @RequestBody Cluster clusterDetails) {
        return clusterRepository.findById(clusterId)
                .map(cluster -> {
                    cluster.setName(clusterDetails.getName());
                    cluster.setDescricao(clusterDetails.getDescricao());
                    Cluster updatedCluster = clusterRepository.save(cluster);
                    return ResponseEntity.ok().body(updatedCluster);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Deletar um cluster
    @DeleteMapping("/{clusterId}")
    public ResponseEntity<?> deleteCluster(@PathVariable Integer clusterId) {
        return clusterRepository.findById(clusterId)
                .map(cluster -> {
                    clusterRepository.delete(cluster);
                    return ResponseEntity.ok().build();
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}