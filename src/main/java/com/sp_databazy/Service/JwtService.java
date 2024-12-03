package com.sp_databazy.Service;

import com.sp_databazy.Entity.Pouzivatel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.nio.charset.StandardCharsets;

@Service
public class JwtService {

    private final String SECRET = "my-super-secret-key-which-is-long-enough-for-hmacsha";
    private final Key SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));


    // Use a securely generated key with a size >= 256 bits (32 bytes)
    //private final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Generating the token
//    public String generateToken(String email) {
////        Map<String, Object> claims = new HashMap<>();
////        return createToken(claims, email);
//        Map<String, Object> claims = new HashMap<>();
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(email)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hodín
//                .signWith(SECRET_KEY, SignatureAlgorithm.HS256) // Používaj rovnaký kľúč
//                .compact();
//
//
//    }

    public String generateToken(Pouzivatel pouzivatel) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", pouzivatel.getId());
        claims.put("email", pouzivatel.getEmail());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(pouzivatel.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hodín
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }


    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)  // The subject (email) will be stored here
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))  // Token valid for 10 hours
                .signWith(SECRET_KEY)  // Use the securely generated key here
                .compact();
    }

    // Validate the token
    public boolean validateToken(String token, String email) {
        final String tokenEmail = extractEmail(token);
        return (tokenEmail.equals(email) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

//    public String extractEmail(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Long extractUserId(String token) {
        return extractAllClaims(token).get("userId", Long.class);
    }

    private Claims extractAllClaims(String token) {

        System.out.println("Generated Token: " + token);
        System.out.println("SECRET_KEY: " + SECRET);


        return Jwts.parserBuilder()  // New parserBuilder() method used instead of deprecated 'parser()' method
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();


    }
}
