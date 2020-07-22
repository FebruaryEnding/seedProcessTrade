package com.yao.trade.controller;

import com.yao.trade.common.PageResult;
import com.yao.trade.common.Result;
import com.yao.trade.dao.ISeedAffixDao;
import com.yao.trade.dao.ISeedDao;
import com.yao.trade.dao.dto.*;
import com.yao.trade.service.IAnnoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Api("种子")
@RequestMapping("seed")
public class SeedController {

    @Autowired
    private ISeedDao seedDao;

    @Autowired
    private ISeedAffixDao affixDao;

    @Autowired
    private IAnnoService annoService;

    @ApiOperation("添加种子")
    @PostMapping
    public Result addSeed(@RequestBody List<SeedRequestDTO> seedRequestDTO, HttpServletRequest servletRequest) {
        seedDao.save(seedRequestDTO, servletRequest);
        return new Result.Builder().data(null).msg("添加成功").success(true).build();
    }

    @ApiOperation("批量添加种子")
    @PostMapping("/mulAdd")
    public Result mulAddSeed(@RequestBody SeedMulAddRequestDto seedRequestDTO, HttpServletRequest servletRequest) {
        seedDao.mulAdd(seedRequestDTO, servletRequest);
        return new Result.Builder().data(null).msg("添加成功").success(true).build();
    }

    @ApiOperation("添加种子")
    @PostMapping("/one")
    public Result addOneSeed(@RequestBody SeedRequestDTO seedRequestDTO, HttpServletRequest servletRequest) {
        seedDao.saveOne(seedRequestDTO, servletRequest);
        return new Result.Builder().data(null).msg("添加成功").success(true).build();
    }

    @ApiOperation("查询种子")
    @GetMapping
    public PageResult query(SeedQuery query) {
        PageResult result = seedDao.query(query);
        return result;
    }

    @ApiOperation("查询种子词缀")
    @GetMapping("/affix")
    public Result findAll() {
        AffixQuery query = new AffixQuery();
        List<AffixResponseDTO> all = affixDao.findAll(query);
        return new Result.Builder().data(all).msg("查询成功").success(true).build();
    }


    @ApiOperation("查询公告")
    @GetMapping("/anno")
    public String getAnno() {
        return annoService.getAnno();
    }

    @ApiOperation("删除")
    @DeleteMapping("/{id}/{userId}")
    public Result delete(@PathVariable("id") String id, @PathVariable("userId") String userId, HttpServletRequest servletRequest) {
        String result = seedDao.delete(id, userId, servletRequest);
        return new Result.Builder().msg(result).success(true).build();
    }
}
