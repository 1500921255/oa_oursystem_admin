package com.example.demo.controller;


import com.example.demo.common.Result;
import com.example.demo.entity.OaNotice;
import com.example.demo.entity.OaNotifyRecord;
import com.example.demo.service.EmployeeUserService;
import com.example.demo.service.OaNoticeService;
import com.example.demo.service.OaNotifyRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ymy
 * @since 2020-10-10
 */
@RestController
@RequestMapping("/oa-notice")
public class OaNoticeController {
    @Autowired
    private OaNoticeService oaNoticeService;
    @Autowired
    private OaNotifyRecord oaNotifyRecord;
    @Autowired
    private OaNotifyRecordService oaNotifyRecordService;
    @Autowired
    private EmployeeUserService employeeUserService;
    @GetMapping("notice")
    public Result SelectList(){
        List list=oaNoticeService.SelectList();
        if(list!=null){
            return Result.succ(200, "查询成功",true, list);
        }else {
            return Result.succ(200, "暂无数据", true, list);
        }
    }
//    @GetMapping("notice")
//    public Result SelectPage(@RequestParam("currentPage") int currentPage,@RequestParam("pagesize") int pagesize){
////        Map map=oaNoticeService.SelectPage(currentPage,pagesize);
////        if(map!=null){
////            return Result.succ(200, "查询成功",true, map);
////        }else {
////            return Result.succ(200, "暂无数据", true, map);
////        }
//        return Result.fail("123",null);
//    }
    @PostMapping("notice")
    public Result insertOne(@RequestBody OaNotice oaNotice){
        System.out.println(oaNotice.getId());
        int i= oaNoticeService.insertOne(oaNotice);
        if(i>0){
            return Result.succ(200, "添加成功",true, null);
        }else {
            return Result.fail(400, "暂无数据", false, null);
        }
    }
    @DeleteMapping("notice")
    public Result deleteOne(@RequestParam int id){
        int i=oaNoticeService.deleteOne(id);
        if(i>0){
            return Result.succ(200, "删除成功",true, null);
        }else {
            return Result.fail(400, "暂无数据", false, null);
        }
    }
    @PutMapping("notice")
    public Result sendOne(@RequestBody OaNotice oaNotice){
        int i=oaNoticeService.sendOne(oaNotice);
        if(i>0){
            int[] employeeId=employeeUserService.getemployeeId();
            List list1=new ArrayList();
            for(int i1=0;i1<employeeId.length;i1++){
                OaNotifyRecord oaNotifyRecord=new OaNotifyRecord();
                oaNotifyRecord.setOaNotifyId(oaNotice.getId());
                oaNotifyRecord.setEmployeeId(employeeId[i1]);
                list1.add(oaNotifyRecord);
                System.out.println(list1.get(i1));
            }
            oaNotifyRecordService.insertList(list1);
            return Result.succ(200, "修改标志成功",true, null);
        }else {
            System.out.println("21321");
            return Result.fail(400, "暂无数据", false, null);
        }
    }
}
