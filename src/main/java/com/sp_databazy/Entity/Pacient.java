package com.sp_databazy.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "Pacient")
public class Pacient {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "cislo_poistenca")
    private String cisloPoistenca;

//    @Column(name = "cislo_poistovne", length = 2)
//    private String cisloPoistovne;

    @ManyToOne()
    @JoinColumn(name = "pouzivatel_id")
    private Pouzivatel pouzivatelId;

    @ManyToOne
    @JoinColumn(name = "cislo_pois", nullable = false)
    private Poistovna poistovna;

}
