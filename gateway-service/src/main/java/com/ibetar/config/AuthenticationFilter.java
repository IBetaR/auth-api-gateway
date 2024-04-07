package com.ibetar.config;

import com.ibetar.service.JWTUtil;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.RequestRateLimiterGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RefreshScope
@Component
public class AuthenticationFilter implements GatewayFilter {
    private final RouteValidator routeValidator;
    private final JWTUtil jwtUtil;
    private final RequestRateLimiterGatewayFilterFactory rateLimiter;

    public AuthenticationFilter(
            RouteValidator routeValidator,
            JWTUtil jwtUtil,
            RequestRateLimiterGatewayFilterFactory rateLimiter
    ) {
        this.routeValidator = routeValidator;
        this.jwtUtil = jwtUtil;
        this.rateLimiter = rateLimiter;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();

        if (routeValidator.isSecured.test(request)) {
            if (authenticationMissing(request)) {
                return onError(exchange);
            }

            final String token = request.getHeaders().getOrEmpty(HttpHeaders.AUTHORIZATION).get(0);

            if (jwtUtil.isExpiredToken(token)) {
                return onError(exchange);
            }
        }

        return chain.filter(exchange);
    }

    private boolean authenticationMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION);
    }

//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        ServerHttpRequest request = exchange.getRequest();
//
//        // Check if the request is rate-limited
//        // TODO: Verify if works properly
//        return rateLimiter.apply(new RequestRateLimiterGatewayFilterFactory.Config())
//                .filter(exchange, chain)
//                .flatMap(isRateLimited -> {
//
//                    if (routeValidator.isSecured.test(request)) {
//                        if (authenticationMissing(request)) {
//                            return onError(exchange);
//                        }
//
//                        final String token = request.getHeaders().getOrEmpty(HttpHeaders.AUTHORIZATION).get(0);
//
//                        if (jwtUtil.isExpiredToken(token)) {
//                            return onError(exchange);
//                        }
//                    }
//
//                    // Proceed with the filter chain if the request is not rate-limited
//                    return chain.filter(exchange);
//                });
//    }



    private Mono<Void> onError(
            ServerWebExchange exchange
    ) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);

        return response.setComplete();
    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus status) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        return response.setComplete();
    }

//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//
//        ServerHttpRequest request = exchange.getRequest();
//
//        if (routeValidator.isSecured.test(request)) {
//            if (authenticationMissing(request)) {
//                return onError(exchange, HttpStatus.UNAUTHORIZED);
//            }
//
//            final String token = request.getHeaders().getOrEmpty("Authorization").get(0);
//
//            if (jwtUtil.isExpiredToken(token)) {
//                return onError(exchange, HttpStatus.UNAUTHORIZED);
//            }
    //   TODO: Verify implementation of extractAuthenticatedUsername(token)
    //            exchange
//                    .getRequest()
//                    .mutate()
//                    .header(
//                            "loggedUser",
//                            jwtUtil.extractAuthenticatedUsername(token)
//                    );
//
//            // Apply rate limiting
//            return rateLimiter.isAllowed(keyResolver.resolve(exchange), "API_RATE_LIMIT")
//                    .flatMap(allowed -> {
//                        if (!allowed) {
//                            return onError(exchange, HttpStatus.TOO_MANY_REQUESTS);
//                        }
//                        return chain.filter(exchange);
//                    });
//        }
//
//        return chain.filter(exchange);
//    }

}