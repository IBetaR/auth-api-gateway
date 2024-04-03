package com.ibetar.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaListenerService {
    @KafkaListener(topics = "mails")
    public void listenMailMessages(String message) {
        var msgParts = message.split(":");
        log.info("Kafka listener service is performing message {} with content: {}.",
                msgParts[0],
                msgParts[1]
        );
    }

}