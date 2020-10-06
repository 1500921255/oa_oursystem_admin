package com.example.demo.controller;


import com.example.demo.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bus")
public class BusController {

    @GetMapping(value = "/error")
    public Result error(){

        return Result.fail("未登录或未授权");
    }
}
