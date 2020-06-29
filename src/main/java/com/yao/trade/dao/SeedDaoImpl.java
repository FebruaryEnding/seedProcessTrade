package com.yao.trade.dao;

import com.yao.trade.common.BeanCopyUtils;
import com.yao.trade.dao.dto.PageQuery;
import com.yao.trade.dao.dto.PageResult;
import com.yao.trade.dao.dto.SeedRequestDTO;
import com.yao.trade.dao.dto.SeedResponseDTO;
import com.yao.trade.domain.SeedEntity;
import com.yao.trade.service.ISeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

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
    public void save(SeedRequestDTO seedRequestDTO) {
        SeedEntity seedEntity = BeanCopyUtils.copy(seedRequestDTO, SeedEntity.class);
        seedEntity.setCreatedTime(new Date());
        seedService.save(seedEntity);
    }

    @Override
    public PageResult query(PageQuery pageQuery) {
        PageResult seedEntities = seedService.query(new Specification<SeedEntity>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<SeedEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        }, PageRequest.of(pageQuery.getPage(), pageQuery.getPageSize(), Sort.by(new Sort.Order(Sort.Direction.DESC, "createdTime"))));

        List<SeedEntity> content = seedEntities.getRows();
        List list = BeanCopyUtils.copyList(content, SeedResponseDTO.class);
        seedEntities.setRows(list);
        return seedEntities;
    }


}
