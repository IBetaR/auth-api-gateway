package com.ibetar.entity;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document
public class UserVO {
    @Id
    private String id;
    private String name;
    private String username;
    private String email;
    List<String> roles;
    private String profileImageId;
    boolean isEnabled;
    private String lastLogin;
}
