package com.sp_databazy.Request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.objenesis.instantiator.util.UnsafeUtils;

import java.util.UUID;

@Getter
@Setter
public class UlozOddelenieRequest {

    private String nazov;
    private UUID veduciDoktorId;
}
