package com.example.demo.controller;


import com.example.demo.common.Result;
import com.example.demo.entity.Permission;
import com.example.demo.entity.Role;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ymy
 * @since 2020-10-07
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping(value = "list")
    public Result RoleList(){
        List<Role> list = roleService.roleList();
        return Result.succ(list);
    }

    @DeleteMapping(value = "RoleDelete/{id}")
    public Result RoleDelete(@PathVariable("id") int id){
       int i =  roleService.delete(id);
       if(i == 1){
           return Result.succ(null);
       }else {
           return Result.fail(null);
       }

    }

    @PutMapping(value = "RoleUpdate")
    public Result RoleUpdate(@RequestBody Role role){
        System.out.println(role);
        int i =  roleService.update(role);
        if(i == 1){
            return Result.succ(null);
        }else {
            return Result.fail(null);
        }
    }

    @PostMapping(value = "RoleInsert")
    public Result RoleInsert(@RequestBody Role role){
        int i =  roleService.insert(role);
        if(i == 1){
            return Result.succ(null);
        }else {
            return Result.fail(null);
        }
    }

    @GetMapping(value = "RolesPermission/{id}")
    public Result UserRoles(@PathVariable("id")int id){
        Set<Permission> set = roleService.getRolePermissions(id);
        return Result.succ(set);
    }

}
