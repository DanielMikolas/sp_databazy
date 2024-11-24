package com.sp_databazy.Repository;

import com.sp_databazy.Entity.Oddelenie;
import com.sp_databazy.Entity.Pacient;
import com.sp_databazy.Entity.PacientOddelenie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PacientOddelenieRepository extends JpaRepository<PacientOddelenie, UUID> {

    Optional<PacientOddelenie> findByPacientIdIdAndOddelenieId(Pacient pacient, Oddelenie oddelenie);
    // Získanie všetkých záznamov podľa oddelenia
    List<PacientOddelenie> findByOddelenieId(Oddelenie oddelenie);

    // Získanie všetkých záznamov podľa pacienta
    List<PacientOddelenie> findByPacientIdId(Pacient pacient);
}
