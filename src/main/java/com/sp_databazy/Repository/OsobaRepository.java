package com.sp_databazy.Repository;

import com.sp_databazy.Entity.Osoba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OsobaRepository extends JpaRepository<Osoba, UUID> {
    boolean existsByRodneCislo(String rodneCislo);
    //Osoba findByRodneCislo(String rodneCislo);
    Optional<Osoba> findByRodneCislo(String rodneCislo);
}
