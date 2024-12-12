package com.sp_databazy.Repository;

import com.sp_databazy.Entity.Pacient;
import com.sp_databazy.Entity.Vysetrenie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VysetrenieRepository extends JpaRepository<Vysetrenie, UUID> {

    List<Vysetrenie> findByPacient(Pacient pacient);


}
