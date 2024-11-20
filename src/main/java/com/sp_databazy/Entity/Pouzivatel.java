package com.sp_databazy.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sp_databazy.Entity.Enums.Rola;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "Pouzivatel")
public class Pouzivatel {

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "djsmPouzivatel_seq")
//    @SequenceGenerator(name = "djsmPouzivatel_seq", sequenceName = "DJSMPouzivatel_SEQ", allocationSize = 1)
//    @Column(nullable = false, columnDefinition = "NUMBER(38,0)")
//    private BigDecimal id;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String email;

    @Column
    private String heslo;

    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private Rola rola;

    @ManyToOne()
    @JoinColumn(name = "osoba_id")
    private Osoba osobaId;

    @JsonIgnore
    @OneToMany(mappedBy = "pouzivatelId", cascade = CascadeType.ALL)
    private Set<Sestra> sestra;

    @JsonIgnore
    @OneToMany(mappedBy = "pouzivatelId", cascade = CascadeType.ALL)
    private Set<Pacient> pacient;

    @JsonIgnore
    @OneToMany(mappedBy = "pouzivatelId", cascade = CascadeType.ALL)
    private Set<Doktor> doktor;
}
