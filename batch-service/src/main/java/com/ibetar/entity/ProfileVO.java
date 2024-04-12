package com.ibetar.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "profiles")
public class ProfileVO {
    @Id
    private String id;
    private String name;
    private String email;
    private String profileImageId;
    private boolean kycVerified;
    private String legalType;
    private String identificationType;
    private String identificationNumber;
    private String country;
    private int pointsBalance;
    private boolean isActive;
    private String rating;
    private boolean isDeleted;
    private String createdDate;
    private String lastUpdateDate;
}