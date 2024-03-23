package com.ibetar.service;

import com.ibetar.entity.AuthRequest;
import com.ibetar.entity.AuthResponse;
import com.ibetar.entity.UserVO;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final JWTUtil jwtUtil;
    private final RestTemplate restTemplate;

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

}