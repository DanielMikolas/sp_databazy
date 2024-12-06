package com.sp_databazy.Entity;

import com.ctc.wstx.shaded.msv_core.datatype.xsd.UnicodeUtil;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "Prijem")
public class Prijem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "pacient_id", nullable = false)
    private Pacient pacientId;

    @Column(name = "datum_prijmu", nullable = false)
    private LocalDateTime datumPrijmu;

    @Column(name = "dovod_prijmu", nullable = false)
    private String dovodPrijmu;

    @ManyToOne
    @JoinColumn(name = "diagnoza", referencedColumnName = "kod", nullable = false)
    private Diagnoza diagnoza;

    @Column(name = "stav_pri_prijme", nullable = false)
    private String stavPriPrijme;

    @ManyToOne
    @JoinColumn(name = "osetrujuci_lekar_id", nullable = false)
    private Doktor osetrujuciLekarId;

    @ManyToOne
    @JoinColumn(name = "oddelenie_id", nullable = false)
    private Oddelenie oddelenieId;

    @ManyToOne
    @JoinColumn(name = "prijem_sestra_id", nullable = false)
    private Sestra prijemSestraId;

    @Column(name = "zaznam_o_prijme")
    private String zaznamOPrijme;

    @Column(name = "poznamky")
    private String poznamky;

    @Column(name = "os_veci_pac")
    private String osVeciPac;
}
