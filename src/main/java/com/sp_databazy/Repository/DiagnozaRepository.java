package com.sp_databazy.Repository;

import com.sp_databazy.Entity.Diagnoza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnozaRepository extends JpaRepository<Diagnoza, String> {
}
