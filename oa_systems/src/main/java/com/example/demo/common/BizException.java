package com.example.demo.common;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BizException{

    @ExceptionHandler(AuthorizationException.class)
    public Result a(AuthorizationException e){

        return Result.fail("您暂无此权限该操作");
    }


}
