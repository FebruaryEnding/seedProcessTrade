package com.yao.trade.service;

import com.yao.trade.domain.SeedAffixEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ISeedAffixService {
    List<SeedAffixEntity> findAll(Specification<SeedAffixEntity> specification);
}
