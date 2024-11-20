package com.sp_databazy.Request;

import com.sp_databazy.Entity.Enums.Typ;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UlozOsobaRequest {

    private String meno;
    private String priezvisko;

    private LocalDate datumNarodenia;
    private String rodneCislo;
    private String adresa;
    private Typ typ;
}
