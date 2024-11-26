package com.sp_databazy.Repository;

import com.sp_databazy.Entity.Strava;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StravaRepository extends JpaRepository<Strava, UUID> {
}
