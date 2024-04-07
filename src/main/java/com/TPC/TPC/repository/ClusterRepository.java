package com.TPC.TPC.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TPC.TPC.model.Cluster;
public interface ClusterRepository extends JpaRepository<Cluster, Integer> {}
