package com.ibetar.service;

import com.ibetar.entity.ProfileVO;
import com.ibetar.repository.ProfileVORepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProfileVOService {
    private final ProfileVORepository repository;

    public Page<ProfileVO> getProfiles(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
