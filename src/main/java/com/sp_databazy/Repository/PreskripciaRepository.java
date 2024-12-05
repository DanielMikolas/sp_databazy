package com.sp_databazy.Repository;

import com.sp_databazy.Entity.Pacient;
import com.sp_databazy.Entity.Preskripcia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PreskripciaRepository extends JpaRepository<Preskripcia , UUID> {

    // Vyhľadanie predpisov podľa ID pacienta
    List<Preskripcia> findByPacient(Pacient pacient);
}
