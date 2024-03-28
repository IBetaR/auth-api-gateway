package com.ibetar.config;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
public class RouteValidator {
    public static final List<String> openEndpoints = List.of(
            "/auth/register"
    );

    public Predicate<ServerHttpRequest> isSecured =
            serverHttpRequest -> openEndpoints.stream()
                    .noneMatch(uri -> serverHttpRequest.getURI().getPath().contains(uri));

    // Define open endpoints and endpoints that require rate limiting
    public static final List<String> rateLimitedEndpoints = List.of(
            "/users/"
    );

    // Predicate to check if the request should be rate-limited
    public Predicate<ServerHttpRequest> shouldRateLimit =
            serverHttpRequest -> rateLimitedEndpoints.stream()
                    .anyMatch(uri -> serverHttpRequest.getURI().getPath().contains(uri));

}