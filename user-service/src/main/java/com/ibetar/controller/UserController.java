package com.ibetar.controller;

import com.ibetar.entity.UserVO;
import com.ibetar.service.EmailService;
import com.ibetar.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final UserService userService;
    private final EmailService emailService;

    @PostMapping
    @Operation(summary = "The user can register")
    public ResponseEntity<UserVO> register(@RequestBody UserVO user) {
        LOGGER.info("Registering user with email: " + user.getEmail());
        emailService.sendUserWelcomeEmail(user);
        return ResponseEntity.ok(userService.register(user));
    }

    @GetMapping("secured")
    @Operation(summary = "The user retrieve data securely")
    public ResponseEntity<String> getSecuredData() {
        return ResponseEntity.ok("This is a secured and authenticated response");
    }
}
