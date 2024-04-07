package com.ibetar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerVO {
    private String uuid;
    private String name;
    private String email;
    private String confirmEmail;
    private String password;
    private String confirmPassword;
    private String role;

}