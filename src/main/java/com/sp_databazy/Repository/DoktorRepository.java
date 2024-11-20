package com.sp_databazy.Repository;

import com.sp_databazy.Entity.Doktor;
import com.sp_databazy.Entity.Pouzivatel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DoktorRepository extends JpaRepository<Doktor, UUID> {
    Optional<Doktor> findByPouzivatelId(Pouzivatel pouzivatel);
}
