package com.TPC.TPC.UserMaster;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserMasterRepository extends JpaRepository<UserMaster, Integer> {
    Page<UserMaster> findByNome(String userMaster, Pageable pageable);
}