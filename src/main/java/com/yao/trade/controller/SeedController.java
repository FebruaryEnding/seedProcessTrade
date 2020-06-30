package com.yao.trade.controller;

import com.yao.trade.dao.ISeedDao;
import com.yao.trade.dao.dto.PageResult;
import com.yao.trade.dao.dto.SeedQuery;
import com.yao.trade.dao.dto.SeedRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api("种子")
@RequestMapping("seed")
public class SeedController {

    @Autowired
    private ISeedDao seedDao;

    @ApiOperation("添加种子")
    @PostMapping
    public String addSeed(@RequestBody List<SeedRequestDTO> seedRequestDTO) {
        seedDao.save(seedRequestDTO);
        return "添加成功";
    }

    @ApiOperation("查询种子")
    @GetMapping
    public PageResult query(SeedQuery query) {
        PageResult result = seedDao.query(query);
        return result;
    }
}
