package com.yao.trade.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "测试")
@RequestMapping("/test")
public class TestController {

    @ApiOperation("测试get")
    @GetMapping("")
    public String test() {
        return "测试";
    }
}
