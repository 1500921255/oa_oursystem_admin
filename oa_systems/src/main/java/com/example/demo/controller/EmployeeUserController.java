package com.example.demo.controller;


import com.example.demo.common.Result;
import com.example.demo.entity.EmployeeUser;
import com.example.demo.service.EmployeeUserService;
import com.example.demo.service.impl.EmployeeUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public Result login(@RequestParam("employee_user") String employee_user , @RequestParam("employee_pwd") String employee_pwd){
     EmployeeUser employeeUser =   employeeUserService.LOGIN(employee_user,employee_pwd);
     if(employeeUser == null){
         return Result.succ(401,"登陆失败,账号或密码错误",null);
     }else{
         employeeUser.setEmployeePwd("你看不见");
         return Result.succ(200,"登录成功",employeeUser);
     }
    }

    @GetMapping("Page")
    public Result SelectAll(){
        List<EmployeeUser> list = employeeUserService.SelectAll();
        return Result.succ(list);
    }
}
