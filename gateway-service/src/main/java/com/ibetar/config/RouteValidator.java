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
}