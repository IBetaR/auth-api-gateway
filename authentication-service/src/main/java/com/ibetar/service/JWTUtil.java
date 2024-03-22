package com.ibetar.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class JWTUtil {
    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.expiration}")
    private String expiration;
    @Value("${jwt.issuer}")
    private String issuerUrl;

    private Key key;

    @PostConstruct
    public void initKey() {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    private SecretKey getSigningKey() {
        byte[] decodedSecretKey = Base64.getDecoder().decode(secretKey);
        return Keys.hmacShaKeyFor(decodedSecretKey);
    }

    private Claims getClaims(String jwtToken) {
        return Jwts
                .parser()
                .verifyWith((SecretKey) key)
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }

    public String generateToken(String userId, String role, String tokenType) {

        Map<String, String> claims = Map.of("userId", role);

        long expirationMills = "ACCESS".equalsIgnoreCase(tokenType)
                ? Long.parseLong(expiration) * 1000
                : Long.parseLong(expiration) * 1000 * 5;

        final Date now = new Date();
        final Date exp = new Date(now.getTime() * expirationMills);

        return Jwts
                .builder()
                .claims(claims)
                .subject(claims.get("id"))
                .issuedAt(Date.from(Instant.now()))
                .expiration(exp)
                .signWith(getSigningKey())
                .compact();
    }

    private String buildToken(Map<String, String> claims, String tokenType) {
        return Jwts
                .builder()
                .claims(claims)
                .subject(claims.get("id"))
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(
                        Instant.now().plus(15, DAYS)
                ))
                .signWith(getSigningKey())
                .compact();
    }

    public String issueToken(String subject, Map<String, Object> claims) {
        return Jwts
                .builder()
                .claims(claims)
                .subject(subject)
                .issuer(issuerUrl)
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(
                        Instant.now().plus(15, DAYS)
                ))
                .signWith(getSigningKey())
                .compact();
    }

}