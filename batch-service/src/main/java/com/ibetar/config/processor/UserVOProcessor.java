package com.ibetar.config.processor;

import com.ibetar.entity.UserVO;
import org.springframework.lang.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class UserVOProcessor implements ItemProcessor<UserVO, UserVO> {
    @Override
    public UserVO process(@NonNull UserVO userVO) {
        log.info("UserVOProcessor on user: {}", userVO.getName());
        return userVO;
    }
}
