package com.sp_databazy.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    private String email; // Používateľ zadáva email
    private String heslo; // Používateľ zadáva heslo
}
