package com.example.demo.mapper;

import com.example.demo.entity.OaNotice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ymy
 * @since 2020-10-10
 */
@Repository
public interface OaNoticeMapper extends BaseMapper<OaNotice> {
    List getemployeeId();
    int[] getNotify_employeeId();
}
