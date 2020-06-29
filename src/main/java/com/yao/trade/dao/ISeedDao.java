package com.yao.trade.dao;

import com.yao.trade.dao.dto.PageQuery;
import com.yao.trade.dao.dto.PageResult;
import com.yao.trade.dao.dto.SeedRequestDTO;
import org.springframework.data.domain.Page;

public interface ISeedDao {
    void save(SeedRequestDTO seedRequestDTO);

    PageResult query(PageQuery query);


}
