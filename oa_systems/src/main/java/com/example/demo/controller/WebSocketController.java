package com.example.demo.controller;

import com.example.demo.common.WebSocketServer;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@CrossOrigin
@RestController
public class WebSocketController {




    //所有人进行广播
//    @GetMapping("/allsendren")
//    public void ren(@RequestParam String message){
//        try {
//            com.example.demo.common.WebSocketServer.ren(message);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    获取在线人数
    @GetMapping("/get")
    public Map<String, Integer> get(){
        return com.example.demo.common.WebSocketServer.get();
    }
}
