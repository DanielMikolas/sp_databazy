package com.sp_databazy.Repository;

import com.sp_databazy.Entity.Pouzivatel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PouzivatelRepository extends JpaRepository<Pouzivatel, UUID> {
    Optional<Pouzivatel> findPouzivatelByEmail(String email);
}
