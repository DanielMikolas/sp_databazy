package com.sp_databazy.Controller;

import com.sp_databazy.Entity.Pouzivatel;
import com.sp_databazy.Request.LoginRequest;
import com.sp_databazy.Service.JwtService;
import com.sp_databazy.Service.PouzivatelService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PouzivatelService pouzivatelService;



    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getHeslo())
        );

        if (authentication.isAuthenticated()) {


            // Získanie Pouzivatel objektu cez váš servis
            Pouzivatel pouzivatel = pouzivatelService.getPouzivatelByEmail(loginRequest.getEmail())
                    .orElseThrow(() -> new RuntimeException("Používateľ neexistuje"));

            // Generovanie tokenu s detailmi Pouzivatel
            String token = jwtService.generateToken(pouzivatel);

            // Nastavenie JWT ako HTTP-only cookie
            ResponseCookie jwtCookie = ResponseCookie.from("jwt", token)
                    .httpOnly(true)
                    .secure(true)
                    .path("/")
                    .maxAge(60 * 60 * 10) // Expirácia tokenu: 10 hodín
                    .build();

            response.addHeader("Set-Cookie", jwtCookie.toString());

            // Odpoveď obsahujúca token aj informácie o používateľovi
            Map<String, Object> responseBody = Map.of(
                    "message", "Login successful",
                    "token", token,
                    "user", Map.of(
                            "id", pouzivatel.getId(),
                            "email", pouzivatel.getEmail()
                    )
            );

            return ResponseEntity.ok(responseBody);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid credentials"));
        }
    }


    @GetMapping("/validate")
    public ResponseEntity<Map<String, Object>> validateToken(HttpServletRequest request) {
        String token = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("jwt".equals(cookie.getName())) {
                    token = cookie.getValue();
                }
            }
        }

        if (token != null && jwtService.validateToken(token, jwtService.extractEmail(token))) {
            Long userId = jwtService.extractUserId(token);
            String userEmail = jwtService.extractEmail(token);

            return ResponseEntity.ok(Map.of(
                    "status", "authenticated",
                    "user", Map.of(
                            "id", userId,
                            "email", userEmail
                    )
            ));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("status", "unauthenticated"));
    }


    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse response) {
        // Nastavenie prázdnej cookies s rovnakým názvom "jwt", aby sa vymazala
        ResponseCookie jwtCookie = ResponseCookie.from("jwt", "")
                .httpOnly(true)  // Cookie je prístupné len zo servera
                .secure(true)    // Používaj HTTPS
                .path("/")       // Cesta, kde je cookie platná
                .maxAge(0)       // Expirácia okamžite, cookie bude odstránená
                .build();

        // Pridanie cookie na vymazanie do odpovede
        response.addHeader("Set-Cookie", jwtCookie.toString());

        // Vrátenie odpovede, že užívateľ bol úspešne odhlásený
        return ResponseEntity.ok("Logout successful");
    }

}
