package com.ibetar.service;

import com.ibetar.entity.NotificationVO;
import com.ibetar.entity.UpdateRequest;
import com.ibetar.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final NotificationRepository repository;
    public void createNotification(NotificationVO notificationVO) {
        LOGGER.info("Creating notification with id: {} for profile {}.",
                notificationVO.getNotificationId(),
                notificationVO.getProfileName()
        );
        repository.save(notificationVO);
    }

    public void updateNotification(String notificationId, UpdateRequest request) {
        NotificationVO notificationVO = repository.findById(
                UUID.fromString(notificationId))
                .orElseThrow(() -> new RuntimeException("Notification not found")
        );
        LOGGER.info("Updating state to notification with id: {} for profile {}.",
                notificationVO.getNotificationId(),
                notificationVO.getProfileName()
        );

        if (request.content() != null) {
            notificationVO.setContent(request.content());
        }
        notificationVO.setRead(request.isRead());
        notificationVO.setLastUpdateDate(LocalDateTime.now());

        repository.save(notificationVO);
    }
}
