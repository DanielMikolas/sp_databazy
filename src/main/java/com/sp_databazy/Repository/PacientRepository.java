package com.sp_databazy.Repository;

import com.sp_databazy.Entity.Pacient;
import com.sp_databazy.Entity.Pouzivatel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PacientRepository extends JpaRepository<Pacient, UUID> {
    Optional<Pacient> findByPouzivatelId(Pouzivatel pouzivatel);
}
