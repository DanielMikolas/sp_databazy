package com.sp_databazy.Request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class UlozPacientOddelenieRequest {

    private UUID pacientId;
    private UUID oddelenieId;
    private LocalDateTime datumPriradenia;
}
