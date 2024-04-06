package com.ibetar.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class UserVO {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String username;
    private String email;
    List<String> roles;
    private String profileImageId;
    boolean isEnabled;
    private String lastLogin;
}
