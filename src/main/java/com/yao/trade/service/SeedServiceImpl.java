package com.yao.trade.service;

import com.yao.trade.common.PageResult;
import com.yao.trade.domain.SeedEntity;
import com.yao.trade.domain.SeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeedServiceImpl implements ISeedService {

    @Autowired
    private SeedRepository seedRepository;


    @Override
    public  PageResult query(Specification<SeedEntity> specification, PageRequest createdTime) {
        Page<SeedEntity> page = seedRepository.findAll(specification, createdTime);
        return PageResult.newInstace(page.getContent(), page.getTotalPages(), page.getTotalElements(), page.getNumber());

    }

    @Override
    public void save(List<SeedEntity> list) {
        seedRepository.saveAll(list);
    }

    @Override
    public void save(SeedEntity seedEntity) {
        seedRepository.save(seedEntity);
    }

    @Override
    public List<SeedEntity> findByIp(String realIpAddress) {
        return seedRepository.findByIp(realIpAddress);
    }

    @Override
    public void deleteByIpAndId(String realIpAddress, String id) {
         seedRepository.deleteByIpAndId(realIpAddress,id);
    }

    @Override
    public SeedEntity findByIpAndId(String realIpAddress, String id) {
        return seedRepository.findByIpAndId(realIpAddress,id);
    }

    @Override
    public SeedEntity findById(String id) {
        return seedRepository.findById(id).get();
    }

    @Override
    public void deleteById(String id) {
        seedRepository.deleteById(id);
    }

    @Override
    public void deleteMulByRoleNameAndOperateNumber(String roleName, String operateNumber) {
        seedRepository.deleteMulByRoleNameAndOperateNumber(roleName,operateNumber);
    }
}
