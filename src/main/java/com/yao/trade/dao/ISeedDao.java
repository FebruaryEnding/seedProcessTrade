package com.yao.trade.dao;

import com.yao.trade.dao.dto.PageQuery;
import com.yao.trade.dao.dto.PageResult;
import com.yao.trade.dao.dto.SeedQuery;
import com.yao.trade.dao.dto.SeedRequestDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISeedDao {
    void save(List<SeedRequestDTO> seedRequestDTO);

    PageResult query(SeedQuery query);


}
