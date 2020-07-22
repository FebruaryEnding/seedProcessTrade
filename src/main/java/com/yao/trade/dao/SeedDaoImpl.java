package com.yao.trade.dao;

import com.yao.trade.common.BeanCopyUtils;
import com.yao.trade.common.IpUtils;
import com.yao.trade.common.PageResult;
import com.yao.trade.dao.dto.*;
import com.yao.trade.domain.SeedEntity;
import com.yao.trade.service.ISeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SeedDaoImpl implements ISeedDao {

    @Autowired
    private ISeedService seedService;

    @Override
    public void save(List<SeedRequestDTO> seedRequestDTO, HttpServletRequest servletRequest) {
        List<SeedEntity> list = BeanCopyUtils.copyList(seedRequestDTO, SeedEntity.class);
        String realIpAddress = IpUtils.getRealIpAddress(servletRequest);
        List<SeedEntity> seedEntities = seedService.findByIp(realIpAddress);
        if (!CollectionUtils.isEmpty(seedEntities)) {
            throw new RuntimeException("请等10秒钟后再次添加");
        }
        for (SeedEntity seedEntity : list) {
            if (StringUtils.isEmpty(seedEntity.getOperateNumber())) {
                throw new RuntimeException("老哥填下删除密钥");
            }
            seedEntity.setIp(realIpAddress);
            seedEntity.setCreatedTime(new Date());
        }
        seedService.save(list);
    }

    @Override
    public void saveOne(SeedRequestDTO seedRequestDTO, HttpServletRequest servletRequest) {
        SeedEntity seedEntity = BeanCopyUtils.copy(seedRequestDTO, SeedEntity.class);
        String realIpAddress = IpUtils.getRealIpAddress(servletRequest);
        List<SeedEntity> seedEntities = seedService.findByIp(realIpAddress);
        if (!CollectionUtils.isEmpty(seedEntities)) {
            throw new RuntimeException("请等10秒钟后再次添加");
        }
        seedEntity.setIp(realIpAddress);
        seedEntity.setCreatedTime(new Date());
        seedService.save(seedEntity);
    }

    @Override
    @Transactional
    public String delete(String id, String userId, HttpServletRequest servletRequest) {
        SeedEntity seedEntity = seedService.findById(id);
        if (seedEntity == null) {
            return "老哥，消息不存在啊";
        }
        if (!seedEntity.getUserId().equals(userId)) {
            return "老哥，只能删自己的消息，如果自己的消息删不掉请进群反馈";
        }
        seedService.deleteById(id);
        return "删除成功";
    }

    @Override
    @Transactional
    public void mulAdd(SeedMulAddRequestDto seedRequestDTO, HttpServletRequest servletRequest) {
        List<SeedEntity> list = new ArrayList<>();
        String realIpAddress = IpUtils.getRealIpAddress(servletRequest);
        if ( StringUtils.isEmpty(seedRequestDTO.getRoleName())) {
            throw new RuntimeException("请填写角色名称");
        }
        seedService.deleteByUserId(seedRequestDTO.getUserId());
        List<SeedMulAddItemRequestDto> add = seedRequestDTO.getAdd();
        list.addAll(this.toAdd(seedRequestDTO.getUserId(),add, "加", realIpAddress,  seedRequestDTO.getRoleName(), seedRequestDTO.getSellOrBuy(), seedRequestDTO.getServerName()));
        List<SeedMulAddItemRequestDto> addLuck = seedRequestDTO.getAddLuck();
        list.addAll(this.toAdd(seedRequestDTO.getUserId(),addLuck, "幸运加", realIpAddress,  seedRequestDTO.getRoleName(), seedRequestDTO.getSellOrBuy(), seedRequestDTO.getServerName()));
        List<SeedMulAddItemRequestDto> removeAndAdd = seedRequestDTO.getRemoveAndAdd();
        list.addAll(this.toAdd(seedRequestDTO.getUserId(),removeAndAdd, "去加", realIpAddress,  seedRequestDTO.getRoleName(), seedRequestDTO.getSellOrBuy(), seedRequestDTO.getServerName()));
        List<SeedMulAddItemRequestDto> remove = seedRequestDTO.getRemove();
        list.addAll(this.toAdd(seedRequestDTO.getUserId(),remove, "去", realIpAddress,  seedRequestDTO.getRoleName(), seedRequestDTO.getSellOrBuy(), seedRequestDTO.getServerName()));
        seedService.save(list);
    }

    private List<SeedEntity> toAdd(String userId,List<SeedMulAddItemRequestDto> requestDto, String name, String realIpAddress, String roleName,
                                   String sellOrBuy, String serverName) {
        List<SeedEntity> seeEntities = new ArrayList<>();
        if (!CollectionUtils.isEmpty(requestDto)) {
            for (SeedMulAddItemRequestDto seedMulAddItemRequestDto : requestDto) {
                if (seedMulAddItemRequestDto.getNumber() != null && seedMulAddItemRequestDto.getNumber().compareTo(BigDecimal.ZERO) > 0) {
                    SeedEntity seedEntity = new SeedEntity();
                    seedEntity.setCreatedTime(new Date());
                    seedEntity.setIp(realIpAddress);
                    seedEntity.setName(name + seedMulAddItemRequestDto.getName());
                    seedEntity.setUserId(userId);
                    seedEntity.setRoleName(roleName);
                    seedEntity.setSellOrBuy("卖");
                    seedEntity.setPrice(seedMulAddItemRequestDto.getPrice());
                    seedEntity.setUnit(seedMulAddItemRequestDto.getUnit());
                    seedEntity.setServerName(serverName);
                    seedEntity.setSellOrBuy(sellOrBuy);
                    seedEntity.setMul(true);
                    seedEntity.setNumber(seedMulAddItemRequestDto.getNumber());
                    seeEntities.add(seedEntity);
                }
            }
        }
        return seeEntities;
    }


    @Override
    public PageResult query(SeedQuery pageQuery) {
        PageResult seedEntities = seedService.query(new Specification<SeedEntity>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<SeedEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                String keyValue = pageQuery.getKeyValue();
                if (!StringUtils.isEmpty(keyValue)) {
                    Predicate name = criteriaBuilder.like(root.get("name"), "%" + keyValue + "%");
                    Predicate roleName = criteriaBuilder.like(root.get("roleName"), "%" + keyValue + "%");
                    predicates.add(criteriaBuilder.or(name, roleName));
                }
                String serverName = pageQuery.getServerName();
                if (!StringUtils.isEmpty(serverName)) {
                    predicates.add(criteriaBuilder.equal(root.get("serverName"), serverName));
                }
                String sellOrBuy = pageQuery.getSellOrBuy();
                if (!StringUtils.isEmpty(sellOrBuy)) {
                    predicates.add(criteriaBuilder.equal(root.get("sellOrBuy"), sellOrBuy));
                }
                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        }, PageRequest.of(pageQuery.getPage(), pageQuery.getPageSize(), Sort.by(new Sort.Order(Sort.Direction.DESC, "createdTime"))));

        List<SeedEntity> content = seedEntities.getRows();
        List<SeedResponseDTO> list = BeanCopyUtils.copyList(content, SeedResponseDTO.class);
        for (SeedResponseDTO seedResponseDTO : list) {
            seedResponseDTO.generateWhisperAndResult();
        }
        seedEntities.setRows(list);
        return seedEntities;
    }


}
