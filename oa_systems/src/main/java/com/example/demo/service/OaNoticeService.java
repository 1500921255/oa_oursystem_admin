package com.example.demo.service;

import com.example.demo.entity.OaNotice;
import com.baomidou.mybatisplus.extension.service.IService;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ymy
 * @since 2020-10-10
 */
public interface OaNoticeService extends IService<OaNotice> {
    List<OaNotice> SelectList();
//    Map SelectPage(int currentPage,int pagesize);
    int insertOne(OaNotice oaNotice);
    int deleteOne(int id);
    int sendOne(OaNotice oaNotice);
    List<OaNotice> SelectNotices(String NoticeStatus);
}
