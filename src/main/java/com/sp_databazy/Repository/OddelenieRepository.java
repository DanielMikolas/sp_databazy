package com.sp_databazy.Repository;

import com.sp_databazy.Entity.Doktor;
import com.sp_databazy.Entity.Oddelenie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OddelenieRepository extends JpaRepository<Oddelenie, UUID> {
    Optional<Oddelenie> findByNazov(String nazov);
    Oddelenie findByVeduciDoktorId(Doktor doktor);

}
