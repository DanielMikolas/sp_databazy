package com.sp_databazy.Repository;

import com.sp_databazy.Entity.Pacient;
import com.sp_databazy.Entity.Poistovna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PoistovnaRepository extends JpaRepository<Poistovna, String> {
}
