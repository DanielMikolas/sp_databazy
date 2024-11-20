package com.sp_databazy.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sp_databazy.Entity.Enums.Typ;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "Osoba")
public class Osoba {

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "djsmOsoba_seq")
//    @SequenceGenerator(name = "djsmOsoba_seq", sequenceName = "DJSMOSOBA_SEQ", allocationSize = 1)
//    @Column(nullable = false, columnDefinition = "NUMBER(38,0)")
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String meno;

    @Column
    private String priezvisko;

    @Column(name = "datum_narodenia")
    private LocalDate datumNarodenia;

    @Column(name = "rodne_cislo", length = 10)
    private String rodneCislo;

    @Column
    private String adresa;

    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private Typ typ;

    @JsonIgnore
    @OneToMany(mappedBy = "osobaId", cascade = CascadeType.ALL)
    private Set<Pouzivatel> pouzivatel;
}
