package com.TPC.TPC.Cluster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClusterService {
    @Autowired
    private ClusterRepository clusterRepository;

    public Page<Cluster> listarCluster(String cluster, Pageable pageable) {
        if (cluster != null) {
            return clusterRepository.findByName(cluster, pageable);
        }
        return clusterRepository.findAll(pageable);
    }

    public ResponseEntity<Cluster> getClusterById(Integer clusterID) {
        return clusterRepository.findById(clusterID)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Cluster> createCluster(Cluster cluster) {
        Cluster savedCluster = clusterRepository.save(cluster);
        return new ResponseEntity<>(savedCluster, HttpStatus.CREATED);
    }

    public ResponseEntity<Cluster> updateCluster(Integer clusterID, Cluster clusterDetails) {
        return clusterRepository.findById(clusterID)
                .map(cluster -> {
                    cluster.setName(clusterDetails.getName());
                    cluster.setDescricao(clusterDetails.getDescricao());
                    Cluster updatedCluster = clusterRepository.save(cluster);
                    return ResponseEntity.ok(updatedCluster);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<?> deleteCluster(Integer clusterID) {
        return clusterRepository.findById(clusterID)
                .map(cluster -> {
                    clusterRepository.delete(cluster);
                    return ResponseEntity.ok().build();
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
