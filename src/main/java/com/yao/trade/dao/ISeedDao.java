package com.yao.trade.dao;

import com.yao.trade.common.PageResult;
import com.yao.trade.dao.dto.SeedMulAddRequestDto;
import com.yao.trade.dao.dto.SeedQuery;
import com.yao.trade.dao.dto.SeedRequestDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ISeedDao {
    void save(List<SeedRequestDTO> seedRequestDTO, HttpServletRequest servletRequest);

    PageResult query(SeedQuery query);


    void saveOne(SeedRequestDTO seedRequestDTO, HttpServletRequest servletRequest);

    String delete(String id, String operateNumber, HttpServletRequest servletRequest);

    void mulAdd(SeedMulAddRequestDto seedRequestDTO, HttpServletRequest servletRequest);
}
