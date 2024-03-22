package com.ibetar.entity;
public record AuthResponse(
        String token,
        String refreshToken
) {}