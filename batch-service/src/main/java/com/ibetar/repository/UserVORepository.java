package com.ibetar.repository;

import com.ibetar.entity.UserVO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserVORepository extends MongoRepository<UserVO, String> {
}
