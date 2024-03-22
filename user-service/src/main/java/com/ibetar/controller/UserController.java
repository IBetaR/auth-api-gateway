package com.ibetar.controller;

import com.ibetar.entity.UserVO;
import com.ibetar.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final UserService userService;
    @PostMapping
    public ResponseEntity<UserVO> save(@RequestBody UserVO user) {
        return ResponseEntity.ok(userService.save(user));
    }
}
