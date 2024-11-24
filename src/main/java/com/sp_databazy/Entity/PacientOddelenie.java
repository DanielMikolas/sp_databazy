package com.sp_databazy.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "PacientOddelenie")
public class PacientOddelenie {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne()
    @JoinColumn(name = "pacient_id")
    private Pacient pacientIdId;

    @ManyToOne()
    @JoinColumn(name = "oddelenie_id")
    private Oddelenie oddelenieId;

    @Column
    private LocalDateTime datumPriradenia;


}
