package com.example.demo.service;

import com.alibaba.fastjson.JSONArray;

import java.util.List;
import java.util.Map;
public interface CheckInService {
   void checkIn(String employee_user);
   boolean isCheckIn(String userId, long day);
   JSONArray ChenkDay(List list, String employee_user);
}
