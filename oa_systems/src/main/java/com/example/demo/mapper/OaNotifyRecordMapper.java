package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.OaNotifyRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 通知通告发送记录 Mapper 接口
 * </p>
 *
 * @author ymy
 * @since 2020-10-10
 */
@Repository
public interface OaNotifyRecordMapper extends BaseMapper<OaNotifyRecord> {
    int insertList(List list);
    List<OaNotifyRecord> getNotify_record(int employeeId);
    int[] getNotify_employeeId();
}
