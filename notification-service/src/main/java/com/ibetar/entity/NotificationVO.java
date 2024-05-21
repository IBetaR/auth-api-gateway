package com.ibetar.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "notifications")
public class NotificationVO {
    @Id
    private String notificationId;
    private String profileId;
    private String profileName;
    private String profileEmail;
    private String content;
    private boolean isRead;
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdateDate;
}