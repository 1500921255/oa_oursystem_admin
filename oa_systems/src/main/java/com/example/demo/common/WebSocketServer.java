package com.example.demo.common;


import com.alibaba.fastjson.JSON;
import com.example.demo.entity.OaNotifyRecord;
import com.example.demo.service.OaNotifyRecordService;
import com.example.demo.service.impl.OaNotifyRecordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author: zp
 * @Date: 2019-09-20 15:12
 * @Description:
 */
@Component
@ServerEndpoint("/webSocket/chat/{userid}")
public class WebSocketServer {

    // 使用map来收集session，key为roomName，value为同一个房间的用户集合
    // concurrentMap的key不存在时报错，不是返回null
    private static final Map<Integer, Set<Session>> rooms = new ConcurrentHashMap();
//    收集所有人的session
//    key为userid value为session
    private static final Map<Session, String> userNameList = new ConcurrentHashMap();
//    统计房间人数
    private static final Map<String,Integer> roomNumber=new ConcurrentHashMap<>();
    @Autowired
    private OaNotifyRecordService oaNotifyRecordService;
    @OnOpen
    public void connect(@PathParam("userid") String userid, Session session) throws Exception {
        System.out.println("36:"+userid);
        System.out.println(session);
        userNameList.put(session,userid);
        System.err.println("userid"+userid);
        System.out.println("a client has connected!");
    }

    @OnClose
    public void disConnect(@PathParam("userid") String userid, Session session) {
        System.out.println("59:"+userid);
        String s = userNameList.get(session);
        userNameList.remove(session);
        System.out.println("a client has disconnected!");
    }

    @OnMessage
    public void receiveMsg(@PathParam("userid") String userid,
                           String msg, Session session) throws Exception {
        System.out.println(msg);
        // 此处应该有html过滤
        msg = userid + ":" + msg;
        System.out.println(msg);
        // 接收到信息后进行广播
    }

//    所有人进行广播
public  void ren() throws Exception {
    int[] employeeIdArray=oaNotifyRecordService.getNotify_employeeId();
    for (Session session: userNameList.keySet()) {
        for (int i=0;i<employeeIdArray.length;i++){
            if(1==(employeeIdArray[i])){
                List oaNotifyRecord=oaNotifyRecordService.getNotify_record(employeeIdArray[i]);
                System.out.println(oaNotifyRecord);
                String json1= JSON.toJSONString(oaNotifyRecord);
                session.getBasicRemote().sendText(json1);
            }
        }
    }
}
public static Map<String,Integer> get(){
        return roomNumber;
}
}