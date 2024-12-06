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
@Table(name = "Diagnoza")
public class Diagnoza {

    @Id
    private String kod; // PK - kód diagnózy

    @Column(nullable = false)
    private String nazov; // názov diagnózy

}
