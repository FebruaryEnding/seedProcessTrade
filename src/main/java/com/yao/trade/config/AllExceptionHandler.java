package com.yao.trade.config;

import com.yao.trade.common.Code;
import com.yao.trade.common.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AllExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Result RunTimeExceptionHandler(RuntimeException e){
        Result build = new Result.Builder().msg(e.getMessage()).success(false).code(Code.SUCCESS.getCode()).build();
        return build;
    }
}
