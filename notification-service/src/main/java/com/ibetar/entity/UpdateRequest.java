package com.ibetar.entity;

import jakarta.annotation.Nullable;

public record UpdateRequest(
        @Nullable String content,
        boolean isRead
) {}