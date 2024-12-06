package com.sp_databazy.Entity;

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
@Table(name = "VystupnaDokumentacia")
public class VystupnaDokumentacia {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "pacient_id", nullable = false)
    private Pacient pacient;

    @Column(name = "datum_prepustenia", nullable = false)
    private LocalDateTime datumPrepustenia;

    @ManyToOne
    @JoinColumn(name = "diagnoza_pri_prepusteni", referencedColumnName = "kod", nullable = false)
    private Diagnoza diagnoza;

    @Column(name = "stav_pri_prepusteni", nullable = false)
    private String stavPriPrepusteni;

    @Column(name = "odporucenia", nullable = true)
    private String odporucenia;

    @Column(name = "liecba_po_prepusteni", nullable = true)
    private String liecbaPoPrepusteni;

    @ManyToOne
    @JoinColumn(name = "osetrujuci_lekar_id", nullable = false)
    private Doktor osetrujuciLekar;

    @ManyToOne
    @JoinColumn(name = "vystup_sestra_id", nullable = false)
    private Sestra vystupSestra;

    @ManyToOne
    @JoinColumn(name = "prijdem_id", nullable = false)
    private Prijem prijem;

    @Column(name = "vypis_o_priebehu_liecby", nullable = true)
    private String vypisOPriebehuLiečby;

    @Column(name = "posledna_zdravotna_observacia", nullable = true)
    private String poslednaZdravotnaObservacia;

    @Column(name = "poznamky", columnDefinition = "TEXT", nullable = true)
    private String poznamky;
}
