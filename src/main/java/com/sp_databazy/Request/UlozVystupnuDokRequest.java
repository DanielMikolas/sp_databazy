package com.sp_databazy.Request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class UlozVystupnuDokRequest {

    private UUID pacientId;
    private LocalDateTime datumPrepustenia;
    private String diagnozaKod;
    private String stavPriPrepusteni;
    private String odporucenia;
    private String liecbaPoPrepusteni;
    private UUID osetrujuciLekarId;
    private UUID vystupSestraId;
    private UUID prijemId;
    private String vypisOPriebehuLiečby;
    private String poslednaZdravotnaObservacia;
    private String poznamky;
}
