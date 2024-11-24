package com.sp_databazy.Repository;

import com.sp_databazy.Entity.DennePoznamky;
import com.sp_databazy.Entity.Pacient;
import com.sp_databazy.Entity.Sestra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DennePoznamkyRepository extends JpaRepository<DennePoznamky, UUID> {
    List<DennePoznamky> findByPacient(Pacient pacient);
    List<DennePoznamky> findBySestra(Sestra sestra);

}
