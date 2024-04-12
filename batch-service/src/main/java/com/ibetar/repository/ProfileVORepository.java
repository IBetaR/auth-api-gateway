package com.ibetar.repository;

import com.ibetar.entity.ProfileVO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileVORepository extends MongoRepository<ProfileVO, String> {
}