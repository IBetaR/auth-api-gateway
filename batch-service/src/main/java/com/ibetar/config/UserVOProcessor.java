package com.ibetar.config;

import com.ibetar.entity.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.batch.item.ItemProcessor;
@Slf4j
public class UserVOProcessor implements ItemProcessor<UserVO, UserVO> {
    @Override
    public UserVO process(@NotNull UserVO userVO) {
        log.info("UserVOProcessor on user: {}", userVO.getName());
        return userVO;
    }
}
