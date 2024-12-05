package com.sp_databazy.Request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class UlozPreskripciaRequest {

    private String suklKod;
    private UUID pacientId;
    private UUID doktorId;
    private LocalDate datumPredpisu;
    private String davkovanie;
}
