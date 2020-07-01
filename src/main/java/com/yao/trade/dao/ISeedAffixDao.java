package com.yao.trade.dao;

import com.yao.trade.dao.dto.AffixQuery;
import com.yao.trade.dao.dto.AffixResponseDTO;

import java.util.List;

public interface ISeedAffixDao {
    List<AffixResponseDTO> findAll(AffixQuery query);
}
