package com.sp_databazy.Repository;

import com.sp_databazy.Entity.Pouzivatel;
import com.sp_databazy.Entity.Sestra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SestraRepository extends JpaRepository<Sestra, UUID> {
    Optional<Sestra> findByPouzivatelId(Pouzivatel pouzivatel);
}
