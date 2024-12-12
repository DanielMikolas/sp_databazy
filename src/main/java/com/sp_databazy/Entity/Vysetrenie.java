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
@Table(name = "Vysetrenie")
public class Vysetrenie {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "pacient_id", nullable = false)
    private Pacient pacient;

    @ManyToOne
    @JoinColumn(name = "doktor_id", nullable = false)
    private Doktor doktor;

    @Column
    private String popis;

    @Column
    private String typ;

    @Lob //oznacuje ze ide o binarne data
    @Column
    private byte[] priloha;

    @Basic
    @Column
    private String nazov;

    @Column
    private LocalDate datum_nahratia;

    @Column
    private LocalDateTime datumVysetrenia;
}
