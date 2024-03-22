package com.ibetar.controller;

import com.ibetar.entity.AuthRequest;
import com.ibetar.entity.AuthResponse;
import com.ibetar.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("auth")
public class AuthenticationController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {
        LOGGER.info("Register process successful");
//        AuthResponse response = authenticationService.register(request);
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.AUTHORIZATION, response.token());
//        headers.add("Refresh-Token", response.refreshToken());
//        return ResponseEntity.ok()
//                .headers(headers)
//                .body(response);
        return ResponseEntity.ok(authenticationService.register(request));
    }

}