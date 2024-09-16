package com.TPC.TPC.Cluster;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ClusterRepository extends JpaRepository<Cluster, Integer> {
    Page<Cluster> findByName(String cluster, Pageable pageable);
}