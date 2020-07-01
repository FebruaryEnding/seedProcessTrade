package com.yao.trade.controller;

import com.yao.trade.dao.ISeedAffixDao;
import com.yao.trade.dao.ISeedDao;
import com.yao.trade.dao.dto.*;
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

    @ApiOperation("添加种子")
    @PostMapping
    public String addSeed(@RequestBody List<SeedRequestDTO> seedRequestDTO, HttpServletRequest servletRequest) {
        try {
            seedDao.save(seedRequestDTO,servletRequest);
        }catch (RuntimeException e){
            return e.getMessage();
        }
        return "添加成功";
    }

    @ApiOperation("添加种子")
    @PostMapping("/one")
    public String addOneSeed(@RequestBody SeedRequestDTO seedRequestDTO, HttpServletRequest servletRequest) {
        try {
            seedDao.saveOne(seedRequestDTO,servletRequest);
        }catch (RuntimeException e){
            return e.getMessage();
        }
        return "添加成功";
    }

    @ApiOperation("查询种子")
    @GetMapping
    public PageResult query(SeedQuery query) {
        PageResult result = seedDao.query(query);
        return result;
    }

    @ApiOperation("查询种子")
    @GetMapping("/affix")
    public List<AffixResponseDTO> findAll(){
         AffixQuery query =new  AffixQuery();
        return  affixDao.findAll(query);
    }
}
