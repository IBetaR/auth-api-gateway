package com.ibetar.controller;

import com.ibetar.entity.UserVO;
import com.ibetar.service.UserService;
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
    @PostMapping
    public ResponseEntity<UserVO> register(@RequestBody UserVO user) {
        LOGGER.info("Registering user with email: " + user.getEmail());
        return ResponseEntity.ok(userService.register(user));
    }

    @GetMapping("secured")
    public ResponseEntity<String> getSecuredData() {
        return ResponseEntity.ok("This is a secured and authenticated response");
    }
}
