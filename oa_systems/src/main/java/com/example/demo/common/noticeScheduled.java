package com.example.demo.common;

import com.example.demo.entity.OaNotice;
import com.example.demo.mapper.OaNoticeMapper;
import com.example.demo.service.OaNoticeService;
import com.example.demo.service.OaNotifyRecordService;
import com.example.demo.service.impl.OaNoticeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
public class noticeScheduled {
    @Autowired
    private WebSocketServer webSocketServer;
    @Scheduled(cron = "*/5 * * * * ?")
    public void ren(){
        try {
          webSocketServer.ren();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
