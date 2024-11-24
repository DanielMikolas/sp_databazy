package com.sp_databazy.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UlozPoistovnuRequest {

    private String cisloPois;  // Kód poisťovne
    private String nazov; // Názov poisťovne
}
