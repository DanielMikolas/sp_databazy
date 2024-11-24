package com.sp_databazy.Request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class UlozDennePoznamkyRequest {

    private UUID pacientId;
    private UUID sestraId;
    private LocalDateTime datumCas;
    private String poznamka;
}
