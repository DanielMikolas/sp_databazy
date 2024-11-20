package com.sp_databazy.Request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UlozPacientaRequest {

    private String cisloPoistenca;
    private String cisloPoistovne;
    private UUID pouzivatelId;
}
