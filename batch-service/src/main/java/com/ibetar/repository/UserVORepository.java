package com.ibetar.repository;

import com.ibetar.entity.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserVORepository extends JpaRepository<UserVO, Long> {
}
