package com.ibetar.config.processor;
import org.springframework.lang.NonNull;
import com.ibetar.entity.ProfileVO;
import org.springframework.batch.item.ItemProcessor;

public class ProfileVOProcessor implements ItemProcessor<ProfileVO, ProfileVO> {
    @Override
    public ProfileVO process(@NonNull ProfileVO item) {
        return item;
    }
}