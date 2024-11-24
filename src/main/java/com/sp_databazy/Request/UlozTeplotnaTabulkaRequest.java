package com.sp_databazy.Request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class UlozTeplotnaTabulkaRequest {

    private BigDecimal teplota;
    private LocalDateTime zaznamenanie;
    private UUID pacientId;
}
