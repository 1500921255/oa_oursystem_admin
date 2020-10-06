package com.example.demo.common;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class DayNumber {

    public static long getDaySub(String today) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        long day = 0;
        Date beginDate;
        Date endDate;
        try {
            beginDate = format.parse("20201001");
            endDate = format.parse(today);
            day = (endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day;
    }


    public  static List getDay(String fristday,String today,long day1) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        long day=0;
        Date beginDate;
        Date endDate;
        List list=new ArrayList();
        try {
            beginDate = format.parse(fristday);
            endDate = format.parse(today);
            day = (endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for(int i=0;i<=day;i++){
             list.add(i+day1);
        }
        return list;
    }
}
