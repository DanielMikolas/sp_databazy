package com.sp_databazy.Repository;

import com.sp_databazy.Entity.Vizita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VizitaRepository extends JpaRepository<Vizita, UUID> {
}
