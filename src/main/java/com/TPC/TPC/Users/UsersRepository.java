package com.TPC.TPC.Users;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Page<Users> findByNome(String user, Pageable pageable);
}