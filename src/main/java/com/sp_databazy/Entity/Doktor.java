package com.sp_databazy.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "Doktor")
public class Doktor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String specializacia;

    @Column
    private String oddelenie;

    @ManyToOne()
    @JoinColumn(name = "pouzivatel_id")
    private Pouzivatel pouzivatelId;
}
