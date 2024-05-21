package com.ibetar.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "notifications")
public class NotificationVO {
    @Id
    private String id;
    private String content;
    private ProfileVO receiver;

    public NotificationVO(String content, ProfileVO receiver) {
        this.content = content;
        this.receiver = receiver;
    }
}
