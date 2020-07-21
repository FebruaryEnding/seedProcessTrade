package com.yao.trade.controller;

import com.yao.trade.common.Result;
import com.yao.trade.dao.IUserDao;
import com.yao.trade.dao.dto.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api("用户")
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserDao userDao;


    @ApiOperation("查询角色列表")
    @GetMapping
    public Result queryRole(RoleRequestDTO query) {
        List<RoleResponseDTO> result = userDao.queryRole(query);
        return new Result.Builder().data(result).msg("查询成功").success(true).build();
    }

    @ApiOperation("注册用户")
    @PostMapping("register")
    public Result register(@RequestBody  UserRegisterRequestDTO registerRequestDTO){
        userDao.register(registerRequestDTO);
        return new Result.Builder().data("").msg("注册成功").success(true).build();
    }


    @ApiOperation("查询角色列表")
    @GetMapping("/{id}")
    public Result findOne(@PathVariable("id") String id){
        UserResponseDTO responseDTO = userDao.findOne(id);
        return new Result.Builder().data(responseDTO).msg("查询成功").success(true).build();
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public Result login(@RequestBody LoginRequestDto loginRequestDto){
        UserResponseDTO responseDTO = userDao.login(loginRequestDto);
        return new Result.Builder().data(responseDTO).msg("登录成功").success(true).build();
    }
}
