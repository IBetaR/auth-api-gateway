package com.ibetar.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;


@Service
public class JWTUtil {
    @Value("${jwt.secret-key}")
    private String secretKey;

    private SecretKey getSigningKey() {
        byte[] decodedSecretKey = Base64.getDecoder().decode(secretKey);
        return Keys.hmacShaKeyFor(decodedSecretKey);
    }

    private Claims getClaims(String jwtToken) {
        return Jwts
                .parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }

    public boolean isExpiredToken(String jwtToken) {
        try {
            Date today = Date.from(Instant.now());
            return getClaims(jwtToken).getExpiration().before(today);
        } catch (Exception e) {
            return false;
        }
    }

}