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
@Table(name = "Oddelenie")
public class Oddelenie {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nazov;

    @ManyToOne()
    @JoinColumn(name = "veduci_doktor_id")
    private Doktor veduciDoktorId;
}
