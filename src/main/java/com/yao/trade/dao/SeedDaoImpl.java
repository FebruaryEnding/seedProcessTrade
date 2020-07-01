package com.yao.trade.dao;

import com.yao.trade.common.BeanCopyUtils;
import com.yao.trade.dao.dto.PageResult;
import com.yao.trade.dao.dto.SeedQuery;
import com.yao.trade.dao.dto.SeedRequestDTO;
import com.yao.trade.dao.dto.SeedResponseDTO;
import com.yao.trade.domain.SeedEntity;
import com.yao.trade.service.ISeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SeedDaoImpl implements ISeedDao {

    @Autowired
    private ISeedService seedService;

    @Override
    public void save(List<SeedRequestDTO> seedRequestDTO) {
        List<SeedEntity> list = BeanCopyUtils.copyList(seedRequestDTO, SeedEntity.class);
        for (SeedEntity seedEntity : list) {
            seedEntity.setCreatedTime(new Date());
        }
        seedService.save(list);
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
                    predicates.add(criteriaBuilder.like(root.get("name"), "%" + keyValue + "%"));
                }
                String serverName = pageQuery.getServerName();
                if (!StringUtils.isEmpty(serverName)) {
                    predicates.add(criteriaBuilder.equal(root.get("serverName"), serverName));
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
