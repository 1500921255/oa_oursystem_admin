package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.example.demo.common.DayNumber;
import com.example.demo.common.Result;
import com.example.demo.service.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/checkin")
public class CheckInController {

    @Autowired
    private CheckInService checkInService;
    private DayNumber dayNumber;

    @PostMapping("checkin/{employee_user}")
    public Result checkIn(@PathVariable(name = "employee_user") String employee_user) {
        checkInService.checkIn(employee_user);
        return Result.succ(200, "签到成功",true ,null);
    }
    @GetMapping("checkinday")
    public Result ChenkDay(@RequestParam String firstday,@RequestParam String today,String employee_user) {
        long day=dayNumber.getDaySub(firstday);
        List list=dayNumber.getDay(firstday,today,day);
        JSONArray CheckInNum=checkInService.ChenkDay(list,employee_user);
        return Result.succ(CheckInNum);
    }
}
