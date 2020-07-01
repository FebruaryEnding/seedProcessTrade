package com.yao.trade.dao;

import com.yao.trade.dao.dto.AffixQuery;
import com.yao.trade.dao.dto.AffixResponseDTO;
import com.yao.trade.domain.SeedAffixEntity;
import com.yao.trade.service.ISeedAffixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class SeedAffixDaoImpl implements ISeedAffixDao {

    @Autowired
    private ISeedAffixService seedAffixService;

    @Override
    public List<AffixResponseDTO> findAll(AffixQuery affixQuery) {
        List<AffixResponseDTO> responseDTOS = new ArrayList<>();
        List<SeedAffixEntity> entities = seedAffixService.findAll(new Specification<SeedAffixEntity>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<SeedAffixEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!StringUtils.isEmpty(affixQuery.getAffix())) {
                    String affix = affixQuery.getAffix();
                    Predicate chinese = criteriaBuilder.like(root.get("chinese"), "%" + affix + "%");
                    Predicate english = criteriaBuilder.like(root.get("english"), "%" + affix + "%");
                    predicates.add(criteriaBuilder.or(chinese, english));
                }
                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        });

        for (SeedAffixEntity entity : entities) {
            AffixResponseDTO responseDTO = new AffixResponseDTO();
            responseDTO.setAffix(entity.getChinese() + "(" + entity.getEnglish() + ")");
            responseDTOS.add(responseDTO);
        }
        return responseDTOS;
    }
}
