package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.OaNotice;
import com.example.demo.entity.OaNotifyRecord;
import com.example.demo.mapper.OaNoticeMapper;
import com.example.demo.mapper.OaNotifyRecordMapper;
import com.example.demo.service.OaNoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ymy
 * @since 2020-10-10
 */
@Service
public class OaNoticeServiceImpl extends ServiceImpl<OaNoticeMapper, OaNotice> implements OaNoticeService {

    @Autowired
    private OaNoticeMapper oaNoticeMapper;
    private  QueryWrapper<OaNotice> queryWrapper = new QueryWrapper<OaNotice>();
    @Override
    public List<OaNotice> SelectList() {
        List<OaNotice> list=new ArrayList<>();
        list=oaNoticeMapper.selectList(new QueryWrapper<>());
        return list;
    }

//    @Override
//    public Map SelectPage(int currentPage,int pagesize) {
//        IPage<OaNotice> page=new Page<>(currentPage,pagesize);
//        page=oaNoticeMapper.selectPage(page,null);
//        Map map=new HashMap();
//        map.put("Pages",page.getPages());
//        map.put("Total",page.getTotal());
//        map.put("Records",page.getRecords());
//        return map;
//    }

    @Override
    public int insertOne(OaNotice oaNotice) {
        int i=oaNoticeMapper.insert(oaNotice);
        return i;
    }

    @Override
    public int deleteOne(int id) {
        int i=oaNoticeMapper.deleteById(id);
        return i;
    }

    @Override
    public int sendOne(OaNotice oaNotice) {
        int i=oaNoticeMapper.updateById(oaNotice);
        return i;
    }

    @Override
    public List<OaNotice> SelectNotices(String NoticeStatus) {
        System.out.println(NoticeStatus);
        List list=oaNoticeMapper.selectList(queryWrapper.eq("notice_Status",NoticeStatus));
        return list;
    }

}
