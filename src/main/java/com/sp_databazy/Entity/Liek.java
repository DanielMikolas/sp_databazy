package com.sp_databazy.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Data
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "Lieky")
public class Liek {

   //******************* //KOMENTY BRAT S REZERVOU RADSEJ OVERIT AK BUDE POTREBNE NIEKDE // ****************************

    @Id
    private String suklKod; // Primárny kľúč, Ide o kód prideľovaný Štátnym ústavom pre kontrolu liečiv (SÚKL) na identifikáciu lieku.

    @Column(length = 4000)
    private String nazov; //Typicky obsahuje oficiálny názov lieku vrátane obchodného mena.

    @Column(length = 4000)
    private String doplnok; //Napríklad informácia o sile lieku, forme (tablety, kapsuly, sirup) alebo iných detailoch, ktoré dopĺňajú názov.
    //Popis balenia lieku. Obsahuje formu (cps – kapsuly), veľkosť balenia a ďalšie detaily

    @Column()
    private String kodDrz; //Kód držiteľa registrácie. Držiteľ registrácie je spoločnosť zodpovedná za uvádzanie lieku na trh

    @Column()
    private String kodStatu; //Kód krajiny pôvodu alebo krajiny, kde je držiteľ registrácie evidovaný.


    @Column()
    private String atcKod; //Kód ATC (Anatomicko-terapeuticko-chemická klasifikácia).
    //Systém klasifikácie liekov podľa účelu a chemickej štruktúry. Napríklad:
    //N02BE01: Paracetamol
    //A02BC02: Omeprazol

    @Column(length = 4000)
    private String atcNazovSk; //Slovenský názov skupiny v rámci ATC klasifikácie.
    //Napríklad: Analgetiká a antipyretiká pre lieky zo skupiny N02.

    @Column(name = "\"is\"") // Použitie dvojitých úvodzoviek na ošetrenie rezervovaného slova
    private String isFlag; //Flag (príznak) pre špecifický stav alebo kategóriu lieku
    //bude stále slúžiť na označenie špecifického príznaku, ktorý môže byť napríklad indikátorom registrácie alebo povolenia v inom kontexte

    @Column()
    private String regCislo;

    @Column()
    private String expiracia; // Expiračný kód alebo podmienky

    @Column()
    private String vydaj;  //Podmienky výdaja lieku.
   // Napríklad:
   // Rx - na predpis.
    // OTC - voľne predajný.
    //R - vyhradený liek.

    @Column()
    private String typReg; //Napríklad, či ide o centrálne registrovaný liek cez EMA (Európska lieková agentúra) alebo národne registrovaný.
    //Typ registrácie lieku. MRP (Mutual Recognition Procedure) označuje liek registrovaný na základe vzájomného uznania v rámci EÚ.

    @Column()
    private String datumReg;

    @Column()
    private String platnost; // Platnosť lieku (napr. "D" - liek na predpis)

    @Column()
    private String bp; //bude obsahovať hodnotu ako "Ano", čo označuje stav lieku (napr. "schválený").
}
