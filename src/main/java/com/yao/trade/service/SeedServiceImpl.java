package com.yao.trade.service;

import com.yao.trade.dao.dto.PageResult;
import com.yao.trade.domain.SeedEntity;
import com.yao.trade.domain.SeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class SeedServiceImpl implements ISeedService {

    @Autowired
    private SeedRepository seedRepository;

    @Override
    public void save(SeedEntity seedEntity) {
        seedRepository.save(seedEntity);
    }

    @Override
    public  PageResult query(Specification<SeedEntity> specification, PageRequest createdTime) {
        Page<SeedEntity> page = seedRepository.findAll(specification, createdTime);
        return PageResult.newInstace(page.getContent(), page.getTotalPages(), page.getTotalElements(), page.getNumber());

    }
}
