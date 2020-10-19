package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.OaNotifyRecord;

import java.util.List;

/**
 * <p>
 * 通知通告发送记录 服务类qq``     ``
 *
 * @author ymy
 * @since 2020-10-10
 */
public interface OaNotifyRecordService extends IService<OaNotifyRecord> {
    int insertList(List<OaNotifyRecord> list);
    List<OaNotifyRecord> getNotify_record(int employeeId);
    int[] getNotify_employeeId();
}
