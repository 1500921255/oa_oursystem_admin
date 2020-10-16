package com.example.demo.controller;


import com.example.demo.common.Result;
import com.example.demo.entity.Permission;
import com.example.demo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ymy
 * @since 2020-10-07
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    @GetMapping(value = "permsList/{current}")
    public Result permsList(@PathVariable("current")int current){
        Map map = permissionService.permsList(current);
        if(map !=null){
            return Result.succ(map);
        }else{
            return Result.fail(null);
        }
    }

    @PostMapping(value = "permsInsert")
    public Result permsInsert(@RequestBody Permission perms){
        int i = permissionService.permsInsert(perms);
        if(i == 1){
            return Result.succ(i);
        }else {
            return Result.fail(null);
        }
    }
    @DeleteMapping(value = "permsDelete/{id}")
    public Result permsDelete(@PathVariable("id")int id){
        int i = permissionService.permsDelete(id);
        if(i == 1){
            return Result.succ(i);
        }else {
            return Result.fail(null);
        }
    }
    @PutMapping(value = "permsUpdate")
    public Result permsUpdate(@RequestBody Permission perms){
        int i = permissionService.permsUpdate(perms);
        if(i == 1){
            return Result.succ(i);
        }else {
            return Result.fail(null);
        }
    }

    @GetMapping(value = "perms")
    public Result perms(){
        List list = permissionService.perms();
        if(list != null){
            return Result.succ(list);
        }else {
            return Result.fail(null);
        }
    }

    @PostMapping(value = "")
    public Result userPerms(@RequestParam("permsId")int permsId,@RequestParam("roleId") int roleId ){
//        判断是不是管理员   管理员权限不可修改
    //先查询数据库有没有 返回true 或false

//        做if判断
//        true 代表数据库由此权限  删除
//        false 代表数据库没有此权限  添加
return null;
    }

}
