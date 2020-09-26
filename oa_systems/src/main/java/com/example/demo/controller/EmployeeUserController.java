package com.example.demo.controller;


import com.example.demo.common.Result;
import com.example.demo.entity.EmployeeUser;
import com.example.demo.service.EmployeeUserService;
import com.example.demo.service.impl.EmployeeUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lxh
 * @since 2020-09-26
 */
@RestController
@RequestMapping("/employee-user")
public class EmployeeUserController {
    @Autowired
    private EmployeeUserService employeeUserService;

    @GetMapping("login")

    public Result login(){
     EmployeeUser employeeUser =   employeeUserService.LOGIN();
      return Result.succ(200,"操作成功",employeeUser);
    }
}
