package com.yao.trade.service;

import com.yao.trade.dao.dto.PageResult;
import com.yao.trade.domain.SeedEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

public interface ISeedService {
    void save(SeedEntity seedEntity);

    PageResult query(Specification<SeedEntity> specification, PageRequest createdTime);
}
