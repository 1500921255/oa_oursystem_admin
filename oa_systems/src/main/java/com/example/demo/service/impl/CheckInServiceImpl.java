package com.example.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.DayNumber;
import com.example.demo.service.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Service
public class CheckInServiceImpl implements CheckInService {
    private static final String CHECK_IN_PRE_KEY = "USER_CHECK_IN:DAY:";

    private static final String CONTINUOUS_CHECK_IN_COUNT_PRE_KEY = "USER_CHECK_IN:CONTINUOUS_COUNT:";

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private DayNumber dayNumber;

    /**
     * 用户签到
     *
     * @param employee_user 用户账号
     */
    @Override
    public void checkIn(String employee_user) {
        String today = LocalDate.now().format(DATE_TIME_FORMATTER);
        long day=dayNumber.getDaySub(today);
        if(isCheckIn(employee_user, day))
            return;
        stringRedisTemplate.opsForValue().setBit(getCheckInKey(employee_user), day, true);
        System.out.println(employee_user);
//        updateContinuousCheckIn(userId);
    }
    /**
     * 检查用户是否签到
     *
     * @param employee_user
     * @param day
     * @return
     */
    public boolean isCheckIn(String employee_user, long day) {
        Boolean isCheckIn = stringRedisTemplate.opsForValue().getBit(getCheckInKey(employee_user), day);
        System.out.println(isCheckIn);
        return Optional.ofNullable(isCheckIn).orElse(false);
    }
    /**
     * 检查用户的签到情况
     *
     * @return
     */
    @Override
    public JSONArray ChenkDay(List list, String employee_user) {
        JSONArray jsonArray=new JSONArray();
        Calendar cal = Calendar.getInstance();
        String q="";
        for(int i=0;i<list.size();i++){
            JSONObject jsonObject=new JSONObject();
            String[] days=new String[1];
            String[] month=new String[1];
            Boolean isCheckIn=stringRedisTemplate.opsForValue().getBit(getCheckInKey(employee_user),(long)list.get(i));
            if(isCheckIn){
                q="已签到";
            }else {
                q="未签到";
                System.out.println(getCheckInKey(employee_user));
                System.out.println((long)list.get(i));
            }
            if(i<9){
                days[0]="0"+(i+1);
            }else{
                days[0]=i+1+"";
            }
            if(cal.get((Calendar.MONTH)+1)<10){
                month[0] ="0"+(cal.get(Calendar.MONTH) + 1);
            }else {
                month[0]=(cal.get(Calendar.MONTH) + 1)+"";
            }
            jsonObject.put("month",month);
            jsonObject.put("days",days);
            jsonObject.put("CheckRecord",q);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    private String getCheckInKey(String employee_user) {
        return CHECK_IN_PRE_KEY + employee_user;
    }

    private String getContinuousCheckInKey(String employee_user) {
        return CONTINUOUS_CHECK_IN_COUNT_PRE_KEY + employee_user;
    }
}
