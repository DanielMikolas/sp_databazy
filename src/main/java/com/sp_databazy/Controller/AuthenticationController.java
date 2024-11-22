package com.sp_databazy.Controller;

import com.sp_databazy.Request.LoginRequest;
import com.sp_databazy.Service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        try {
            // Logujeme pokus o autentifikáciu
            System.out.println("Pokus o login pre email: " + loginRequest.getEmail());

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getHeslo())
            );

            // Ak je autentifikácia úspešná
            if (authentication.isAuthenticated()) {
                System.out.println("Login úspešný pre email: " + loginRequest.getEmail());
                return jwtService.generateToken(loginRequest.getEmail());
            } else {
                throw new RuntimeException("Invalid login credentials");
            }
        } catch (Exception e) {
            System.err.println("Chyba pri logine: " + e.getMessage());
            throw new RuntimeException("Login failed", e);
        }
    }
}
