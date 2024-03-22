package com.ibetar.service;

import com.ibetar.entity.UserVO;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    public UserVO save(UserVO user) {
        user.setId(UUID.randomUUID().toString());
        user.setRole("USER");
        return user;
    }
}
