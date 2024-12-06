package com.sp_databazy.Repository;

import com.sp_databazy.Entity.Prijem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PrijemRepository extends JpaRepository<Prijem, UUID> {
    List<Prijem> findByPacientId_Id(UUID pacientId);
    List<Prijem> findByOsetrujuciLekarId_Id(UUID lekarId);
    List<Prijem> findByPrijemSestraId_Id(UUID sestraId);
    List<Prijem> findByOddelenieId_Id(UUID oddelenieId);
}
