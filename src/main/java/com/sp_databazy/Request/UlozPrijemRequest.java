package com.sp_databazy.Request;

import com.sp_databazy.Entity.Pacient;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class UlozPrijemRequest {

    private UUID pacientId;
    private LocalDateTime datumPrijmu;
    private String dovodPrijmu;
    private String diagnozaKod;
    private String stavPriPrijme;
    private UUID osetrujuciLekarId;
    private UUID oddelenieId;
    private UUID prijemSestraId;
    private String zaznamOPrijme;
    private String poznamky;
    private String osVeciPac;
}
