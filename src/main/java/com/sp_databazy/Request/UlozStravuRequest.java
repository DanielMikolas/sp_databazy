package com.sp_databazy.Request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UlozStravuRequest {

    private UUID pacientId;
    private String typ;
}
