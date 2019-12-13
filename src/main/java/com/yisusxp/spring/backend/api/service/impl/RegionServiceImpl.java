package com.yisusxp.spring.backend.api.service.impl;

import com.yisusxp.spring.backend.api.model.Region;
import com.yisusxp.spring.backend.api.repository.IRegionRepository;
import com.yisusxp.spring.backend.api.service.IRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RegionServiceImpl implements IRegionService {

    @Autowired
    private IRegionRepository iRegionRepository;
    @Override
    @Transactional(readOnly = true)
    public List<Region> findAllRegiones() {
        return iRegionRepository.findAllRegiones();
    }
}
