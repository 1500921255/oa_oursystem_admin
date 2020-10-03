package com.example.demo.controller;


import com.example.demo.common.Result;
import com.example.demo.entity.EmployeeUser;
import com.example.demo.service.EmployeeUserService;
import com.example.demo.service.impl.EmployeeUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
         return Result.fail(401,"登陆失败,账号或密码错误",null);
     }else{
         employeeUser.setEmployeePwd("你看不见");
         return Result.succ(200,"登录成功",employeeUser);
     }
    }

    @GetMapping(value = "/Page/{current}")
    public Result SelectAll(@PathVariable("current") int current){
        Map map = employeeUserService.Pagelist(current,5);
        return Result.succ(map);
    }

    @DeleteMapping(value = "/DeleteUser/{employeeId}")
    public Result DeleteUser(@PathVariable("employeeId") int employeeId){
        int i = employeeUserService.DeleteUser(employeeId);
        if(i == 1){
            return Result.succ(200,"删除成功",null);
        }else {
            return Result.fail(401,"删除失败",null);
        }
    }

    @PostMapping(value = "InsertUser")
    public Result InsertUser(@RequestParam("employeeUser")EmployeeUser employeeUser){
        int i = employeeUserService.InsertUser(employeeUser);
        if(i == 1){
            return Result.succ(200,"添加成功",null);
        }else {
            return Result.fail(401,"添加失败",null);
        }
    }

    @PutMapping(value = "UpdateUser")
    public Result UpdateUser(@RequestBody EmployeeUser employeeUser){
        System.out.println(employeeUser);
        int i = employeeUserService.UpdateUser(employeeUser);
        if(i == 1){
            return Result.succ(200,"添加成功",null);
        }else {
            return Result.fail(401,"添加失败",null);
        }
    }
}
