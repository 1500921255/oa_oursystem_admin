package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.OaNotice;
import com.example.demo.entity.OaNotifyRecord;
import com.example.demo.mapper.OaNotifyRecordMapper;
import com.example.demo.service.OaNotifyRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 通知通告发送记录 服务实现类
 * </p>
 *
 * @author ymy
 * @since 2020-10-10
 */
@Service
public class OaNotifyRecordServiceImpl extends ServiceImpl<OaNotifyRecordMapper, OaNotifyRecord> implements OaNotifyRecordService {

    @Autowired
    private OaNotifyRecordMapper oaNotifyRecordMapper;
    private QueryWrapper<OaNotice> queryWrapper = new QueryWrapper<OaNotice>();

    @Override
    public int insertList(List list) {
        System.out.println(  oaNotifyRecordMapper.getNotify_record(1));
        int i=oaNotifyRecordMapper.insertList(list);
        return i;
    }

    @Override
    public List<OaNotifyRecord> getNotify_record(int employeeId) {
        List<OaNotifyRecord> list=oaNotifyRecordMapper.getNotify_record(employeeId);
        return list;
    }

    @Override
    public int[] getNotify_employeeId() {
        int[] employeeIdArray=oaNotifyRecordMapper.getNotify_employeeId();
        return employeeIdArray;
    }
}
