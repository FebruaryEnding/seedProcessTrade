package com.yao.trade.service;

import com.yao.trade.dao.dto.PageResult;
import com.yao.trade.domain.SeedEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ISeedService {


    PageResult query(Specification<SeedEntity> specification, PageRequest createdTime);

    void save(List<SeedEntity> list);
    void save(SeedEntity seedEntity);

    List<SeedEntity> findByIp(String realIpAddress);

    void deleteByIpAndId(String realIpAddress, String id);

    SeedEntity findByIpAndId(String realIpAddress, String id);

    SeedEntity findById(String id);

    void deleteById(String id);
}
