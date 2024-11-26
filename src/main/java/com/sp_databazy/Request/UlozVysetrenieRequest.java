package com.sp_databazy.Request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class UlozVysetrenieRequest {

    private UUID pacientId;
    private UUID doktorId;
    private String popis;
    private String typ;
    private byte[] priloha;
    private LocalDateTime datumVysetrenia;
}
