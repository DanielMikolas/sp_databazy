package com.sp_databazy.Request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class UlozVizituRequest {

    private UUID pacientId;
    private UUID doktorId;
    private UUID sestraId;
    private String poznamky;
    private LocalDateTime datum;
}
