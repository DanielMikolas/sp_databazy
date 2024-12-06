package com.sp_databazy.Repository;

import com.sp_databazy.Entity.VystupnaDokumentacia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VystupnaDokRepository extends JpaRepository<VystupnaDokumentacia, UUID> {
    List<VystupnaDokumentacia> findByPacientId(UUID pacientId);

    List<VystupnaDokumentacia> findByOsetrujuciLekarId(UUID osetrujuciLekarId);

    List<VystupnaDokumentacia> findByVystupSestraId(UUID vystupSestraId);

    List<VystupnaDokumentacia> findByPrijemId(UUID prijemId);
}
