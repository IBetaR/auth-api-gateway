package com.ibetar.service;

import com.ibetar.entity.AuthRequest;
import com.ibetar.entity.AuthResponse;
import com.ibetar.entity.CustomerVO;
import com.ibetar.entity.UserVO;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final JWTUtil jwtUtil;
    private final RestTemplate restTemplate;
    //private final WebClient webClient;

//    public AuthResponse signup(AuthRequest request) {
//        LOGGER.info("Customer Registration started");
//        request.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
//
//        CustomerVO registeredUser = restTemplate.postForObject(
//                "http://user-service/users",
//                request,
//                CustomerVO.class
//        );
//
//        assert registeredUser != null;
//        // TODO: Validate the user exist
//        String jwtToken = jwtUtil.generateToken(
//                registeredUser.getUuid(),
//                registeredUser.getRole(),
//                "ACCESS"
//
//        );
//
//        String refreshToken = jwtUtil.generateToken(
//                registeredUser.getUuid(),
//                registeredUser.getRole(),
//                "REFRESH"
//        );
//        LOGGER.info("Registration successful!");
//        return new AuthResponse(
//                registeredUser.getUuid(),
//                jwtToken,
//                refreshToken
//        );
//    }

    public AuthResponse register(AuthRequest request) {
        LOGGER.info("Registration started");
        request.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        UserVO registeredUser = restTemplate.postForObject(
                "http://user-service/users",
                request,
                UserVO.class
        );

        assert registeredUser != null;
        // TODO: Validate the user exist
        String jwtToken = jwtUtil.generateToken(
                registeredUser.getId(),
                registeredUser.getRole(),
                "ACCESS"
                );

        String refreshToken = jwtUtil.generateToken(
                registeredUser.getId(),
                registeredUser.getRole(),
                "REFRESH"
        );
        LOGGER.info("Registration successful!");
        return new AuthResponse(
                registeredUser.getId(),
                jwtToken,
                refreshToken
        );
    }

    // TODO: Refactor to use WebClient
//    public AuthResponse register(AuthRequest request) {
//        LOGGER.info("Registration started");
//        request.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
//
//        Mono<UserVO> registeredUserMono = webClient.post()
//                .uri("http://user-service/users")
//                .body(BodyInserters.fromValue(request))
//                .retrieve()
//                .bodyToMono(UserVO.class);
//
//        UserVO registeredUser = registeredUserMono.block();
//
//        assert registeredUser != null;
//        // TODO: Validate the user exist
//        String jwtToken = jwtUtil.generateToken(
//                registeredUser.getId(),
//                registeredUser.getRole(),
//                "ACCESS"
//        );
//
//        String refreshToken = jwtUtil.generateToken(
//                registeredUser.getId(),
//                registeredUser.getRole(),
//                "REFRESH"
//        );
//        LOGGER.info("Registration successful!");
//        return new AuthResponse(
//                registeredUser.getId(),
//                jwtToken,
//                refreshToken
//        );
//    }
}