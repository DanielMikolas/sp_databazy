package com.sp_databazy.Request;

import com.sp_databazy.Entity.Enums.Rola;
import com.sp_databazy.Entity.Enums.Typ;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class UlozPouzivatelaRequest {

    // Údaje pre osobu (voliteľné)
    private String meno;
    private String priezvisko;
    private LocalDate datumNarodenia;
    private String rodneCislo;
    private String adresa;
    private Typ typ;

    private String email;
    private String heslo;
    private UUID osobaId;
    private Rola rola;

    // Pre pacientov
    private String cisloPoistenca;
    private String cisloPoistovne;

    // Pre doktorov a sestry
    private String specializacia;
    private String oddelenie;

}
