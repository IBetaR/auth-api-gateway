package com.ibetar.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibetar.entity.NotificationVO;
import com.ibetar.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Consumer;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaListenerService {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final ObjectMapper objectMapper;
    private final NotificationService notificationService;
    @Bean
    @Qualifier("listenNotifications")
    public Consumer<KStream<Object, String>> listenNotifications() {
        LOGGER.info("Listening notifications from Kafka...");
        return input -> {
            input.foreach((k, v) -> {
                try {
                    LOGGER.info("Notifications found from Kafka {}", v);
                    Map<String, Object> message = objectMapper.readValue(
                            v,
                            new TypeReference<>() {}
                    );
                    LOGGER.debug("Processing/mapping Objects/notifications as JSON message from Kafka...");
                    NotificationVO notification = objectMapper.convertValue(
                            message.get("notification"),
                            NotificationVO.class
                    );
                    LOGGER.debug("Notification retrieved from JSON  with id {}", notification.getNotificationId());
                    notificationService.createNotification(notification);

                } catch (JsonProcessingException e) {
                    LOGGER.error("Error processing JSON message: {}", e.getMessage());
                    throw new RuntimeException(e);
                }
            });
        };
    }
}