package com.sp_databazy.Request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UlozDoktoraRequest {

    private String specializacia;
    private String oddelenie;
    private UUID pouzivatelId;
}
