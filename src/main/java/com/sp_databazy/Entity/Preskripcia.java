package com.sp_databazy.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "Preskripcia")
public class Preskripcia {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "sukl_kod", nullable = false)
    private Liek liek;

    @ManyToOne
    @JoinColumn(name = "pacient_id", nullable = false)
    private Pacient pacient;

    @ManyToOne
    @JoinColumn(name = "doktor_id", nullable = false)
    private Doktor doktor;


    @Column(name = "datum_predpisu", nullable = false)
    private LocalDate datumPredpisu;

    @Column(name = "davkovanie", nullable = false)
    private String davkovanie;
}
