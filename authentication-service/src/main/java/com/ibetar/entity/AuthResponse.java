package com.ibetar.entity;
public record AuthResponse(
        String authorizedUserId,
        String token,
        String refreshToken
) {}