package com.TPC.TPC.Users;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Page<Users> findByNome(String user, Pageable pageable);

    Optional<Users> findByEmail(String email);
}