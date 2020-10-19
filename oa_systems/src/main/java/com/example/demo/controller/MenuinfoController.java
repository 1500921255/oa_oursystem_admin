package com.example.demo.controller;


import com.example.demo.common.Result;
import com.example.demo.entity.Menuinfo;
import com.example.demo.service.MenuinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ymy
 * @since 2020-10-07
 */
@RestController
@RequestMapping("/menuinfo")
public class MenuinfoController {

    @Autowired
    MenuinfoService menuinfoService;

    @GetMapping(value = "MenuInfo")
    public Result MenuList(){
        List<Menuinfo> list = menuinfoService.MenuList();
        if(list ==null){
            return Result.fail("查询失败");
        }else{
            return Result.succ(list);
        }
    }

    @PostMapping(value = "MenuInfo")
    public Result MenuInsert(@RequestBody Menuinfo menuinfo){
        int i = menuinfoService.InsertMenu(menuinfo);
        if(i != 1){
            return Result.fail("null");
        }
            return Result.succ("null");
    }

    @PutMapping(value = "MenuInfo")
    public Result MenuUpdate(@RequestBody Menuinfo menuinfo){
        int i = menuinfoService.UpdateMenu(menuinfo);
        if(i != 1){
            return Result.fail("null");
        }
        return Result.succ("null");
    }

    @DeleteMapping(value = "MenuInfo/{id}")
    public Result MenuUpdate(@PathVariable("id")int id ){
        int i = menuinfoService.DeleteMenu(id);
        if(i != 1){
            return Result.fail("null");
        }
        return Result.succ("null");
    }

}
