package com.TPC.TPC.UserCluster;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserClusterRepository extends JpaRepository<UserCluster, Integer> {
    Page<UserCluster> findByUserClusterID(String userCluster, Pageable pageable);
}