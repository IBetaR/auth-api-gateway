package com.ibetar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class UserVO {
    private String id;
    private String name;
    private String email;
    private String password;
    private String role;
}
