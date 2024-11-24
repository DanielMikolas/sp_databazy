package com.sp_databazy.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "Poistovna")
public class Poistovna {

    @Id
    @Column(name = "cislo_pois", length = 2)
    private String cisloPois; // Dvojznakové kódovanie poisťovne (napr. "01")

    @Column(name = "nazov", nullable = false)
    private String nazov; // Názov poisťovne
}
