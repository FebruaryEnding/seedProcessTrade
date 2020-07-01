package com.yao.trade.service;

import com.yao.trade.domain.SeedAffixEntity;
import com.yao.trade.domain.SeedAffixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeedAffixServiceImpl implements ISeedAffixService {

    @Autowired
    private SeedAffixRepository repository;

    @Override
    public List<SeedAffixEntity> findAll(Specification<SeedAffixEntity> specification) {
        return repository.findAll(specification);
    }
}
