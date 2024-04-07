package com.ibetar.service;

import com.ibetar.entity.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendUserWelcomeEmail(UserVO user) {
        kafkaTemplate.send("mails", "user.creation: " + user.getName());
    }
}
