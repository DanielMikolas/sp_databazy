package com.sp_databazy.Repository;

import com.sp_databazy.Entity.Liek;
import com.sp_databazy.Entity.Oddelenie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LiekRepository extends JpaRepository<Liek, String> {
    Optional<Liek> findBySuklKod(String suklKod);
}
