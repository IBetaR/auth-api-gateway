package com.ibetar.config;

import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableHystrix
public class GatewayConfig {
    private final AuthenticationFilter filter;
    public GatewayConfig(AuthenticationFilter filter) { this.filter = filter; }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", p -> p.path("/users/**")
                        .filters(f -> f.requestRateLimiter(
                                config -> config.setRateLimiter(redisRateLimiter()))
                                .filter(filter))
                        .uri("lb://user-service"))
                .route("auth-service", p -> p.path("/auth/**")
                        .filters(f -> f.requestRateLimiter(
                                config -> config.setRateLimiter(redisRateLimiter())
                                        .setDenyEmptyKey(false))
                                .filter(filter))
                        .uri("lb://auth-service"))
                .route("inventory-service", p -> p.path("/inventory/**")
                        .filters(f -> f.requestRateLimiter(
                                        config -> config.setRateLimiter(redisRateLimiter())
                                                .setDenyEmptyKey(false))
                                .filter(filter))
                        .uri("lb://inventory-service"))
                .build();
    }

    @Bean
    public RedisRateLimiter redisRateLimiter() {
        return new RedisRateLimiter(1, 1, 1);
    }


//    @Bean
//    public RouteLocator routes(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("user-service", r -> r.path("/users/**")
//                                .filters(f -> f.filter(filter))
//                                .uri("lb://user-service"))
//                .route("auth-service", r -> r.path("/auth/**")
//                        .filters(f -> f.filter(filter))
//                        .uri("lb://auth-service"))
//                .build();
//    }


//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route(
//                        "r1", r -> r.host("**.ibetarsync.com")
//                        .and()
//                        .path("/baeldung")
//                        .uri("http://ibetarsync.com")
//                )
//                .route(
//                        "myOtherID",r -> r.host("**.ibetarsync.com")
//                        .and()
//                        .path("/myOtherRouting")
//                        .filters(f -> f.prefixPath("/myPrefix"))
//                        .uri("http://othersite.com")
//                        )
//                .build();
//    }

}