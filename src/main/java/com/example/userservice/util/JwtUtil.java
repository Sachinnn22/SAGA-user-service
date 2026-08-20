package com.example.userservice.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {
    
    private static final String SECRET = "MySuperDuperSecretKeyForJwtAuthenticationWhichIsVeryLongAndSecure123456";
    
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    private final long accessTokenValidity = 1000 * 60 * 15; 
    private final long refreshTokenValidity = 1000 * 60 * 60 * 24 * 7; 

    public String generateAccessToken(Long id, String email, String role) {
        return Jwts.builder()
                .subject(email)
                .claim("id", id)     
                .claim("role", role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + accessTokenValidity))
                .signWith(getSigningKey())
                .compact();
    }

    public String generateRefreshToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + refreshTokenValidity))
                .signWith(getSigningKey())
                .compact();
    }
}