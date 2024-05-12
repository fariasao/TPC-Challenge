package com.TPC.TPC.controller;

import com.TPC.TPC.model.Cluster;
import com.TPC.TPC.repository.ClusterRepository;
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
@RequestMapping("clusters")
@CacheConfig(cacheNames = "clusters")
public class ClusterController {

    @Autowired
    private ClusterRepository clusterRepository;

    // Buscar todos os clusters
    @GetMapping
    @Cacheable("clusters")
    public Page<Cluster> listarClusters(
        @RequestParam(required = false) String cluster,
        @PageableDefault(sort = "name", direction = Direction.ASC  ) Pageable pageable
    ) {
        if (cluster != null){
            return clusterRepository.findByName(cluster, pageable);
        }
        return clusterRepository.findAll(pageable);
    }

    // Buscar um cluster pelo ID
    @GetMapping("{clusterID}")
    public ResponseEntity<Cluster> getClusterById(@PathVariable Integer clusterID) {
        return clusterRepository.findById(clusterID)
                .map(cluster -> ResponseEntity.ok().body(cluster))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Criar um novo cluster
    @PostMapping
    @CacheEvict(allEntries = true)
    public ResponseEntity<Cluster> createCluster(@Valid @RequestBody Cluster cluster) {
        Cluster savedCluster = clusterRepository.save(cluster);
        return new ResponseEntity<>(savedCluster, HttpStatus.CREATED);
    }

    // Atualizar um cluster existente
    @PutMapping("{clusterID}")
    @CacheEvict(allEntries = true)
    public ResponseEntity<Cluster> updateCluster(@PathVariable Integer clusterID, @Valid @RequestBody Cluster clusterDetails) {
        return clusterRepository.findById(clusterID)
                .map(cluster -> {
                    cluster.setName(clusterDetails.getName());
                    cluster.setDescricao(clusterDetails.getDescricao());
                    Cluster updatedCluster = clusterRepository.save(cluster);
                    return ResponseEntity.ok().body(updatedCluster);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Deletar um cluster
    @DeleteMapping("{clusterID}")
    @CacheEvict(allEntries = true)
    public ResponseEntity<?> deleteCluster(@PathVariable Integer clusterID) {
        return clusterRepository.findById(clusterID)
                .map(cluster -> {
                    clusterRepository.delete(cluster);
                    return ResponseEntity.ok().build();
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
