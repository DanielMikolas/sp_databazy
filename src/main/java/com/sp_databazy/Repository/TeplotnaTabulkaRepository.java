package com.sp_databazy.Repository;

import com.sp_databazy.Entity.Pacient;
import com.sp_databazy.Entity.TeplotnaTabulka;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TeplotnaTabulkaRepository extends JpaRepository<TeplotnaTabulka, UUID> {
    List<TeplotnaTabulka> findByPacient(Pacient pacient);
}
