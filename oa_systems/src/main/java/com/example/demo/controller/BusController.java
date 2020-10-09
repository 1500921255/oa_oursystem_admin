package com.example.demo.controller;


import com.example.demo.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bus")
public class BusController {

    @GetMapping(value = "/login/error")
    public Result loginError(){

        return Result.fail("未登录");
    }
    @GetMapping(value = "/auth/error")
    public Result authError(){

        return Result.fail("未授权");
    }
}
