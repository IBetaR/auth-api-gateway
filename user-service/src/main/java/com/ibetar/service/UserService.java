package com.ibetar.service;

import com.ibetar.entity.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    public UserVO register(UserVO user) {
        user.setId(UUID.randomUUID().toString());
        user.setName("Test User");
        user.setRole("USER");
        LOGGER.info("User with email {} registered successfully ", user.getEmail());
        return user;
    }
}
