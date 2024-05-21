package com.ibetar.repository;

import com.ibetar.entity.NotificationVO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface NotificationRepository extends MongoRepository<NotificationVO, UUID> {
}
