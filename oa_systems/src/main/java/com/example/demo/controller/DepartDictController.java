package com.example.demo.controller;


import com.example.demo.common.Result;
import com.example.demo.entity.DepartDict;
import com.example.demo.service.DepartDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lxh
 * @since 2020-09-28
 */
@RestController
@RequestMapping("/depart-dict")
public class DepartDictController {
    @Autowired
    private DepartDictService departDictService;

    @GetMapping("departs")
    public Result selectList() {
        List<DepartDict> list = departDictService.SelectList();
        if (list == null) {
            return Result.succ(401, "暂无数据",false, null);
        } else {
            return Result.succ(200, "查询成功",true, list);
        }
    }
    @GetMapping("departPage")
    public Result selectPage(@RequestParam("currentPage") int currentPage,@RequestParam("pagesize") int pagesize) {
        Map map = departDictService.SelectPage(currentPage,pagesize);
        if (map == null) {
            return Result.succ(401, "暂无数据",false, null);
        } else {
            return Result.succ(200, "查询成功",true, map);
        }
    }
    @GetMapping("departDim")
    public Result selectDim() {
        List<DepartDict> list = departDictService.SelectDim();
        if (list == null) {
            return Result.succ(401, "暂无数据",false, null);
        } else {
            return Result.succ(200, "查询成功",true, list);
        }
    }
    @PostMapping("departOne")
    public Result addOneEmployees(@RequestBody DepartDict departDict){
        int i=departDictService.addOneDepartDict(departDict);
        if (i == 1) {
            return Result.succ(401, "暂无数据",false, null);
        } else {
            return Result.succ(200, "添加成功",true,null);
        }
    }
    @DeleteMapping("departOne")
    public Result deleteOneEmployees(@RequestParam int q){
        System.out.println(q);
        int i=departDictService.delete(q);
        if (i == 1) {
            return Result.succ(200, "删除成功",true, null);
        } else {
            return Result.succ(401, "删除失败",false,null);
        }
    }
    @PutMapping("departOne")
    public Result UpdateOneEmployees(@RequestBody DepartDict departDict){
        departDictService.UpdateOne(departDict);
        if (departDict!=null) {
            System.out.println("1");
            return Result.succ(200, "修改成功",true, departDict);
        } else {
            System.out.println("2");
            return Result.succ(401, "修改失败",false,departDict);
        }
    }
}
