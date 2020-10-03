package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.DepartDict;
import com.example.demo.mapper.DepartDictMapper;
import com.example.demo.service.DepartDictService;
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
 * @author lxh
 * @since 2020-09-28
 */
@Service
public class DepartDictServiceImpl extends ServiceImpl<DepartDictMapper, DepartDict> implements DepartDictService{

    @Autowired
    private DepartDictMapper departDictMapper;
    QueryWrapper<DepartDict> queryWrapper = new QueryWrapper();
    @Override
    public List<DepartDict> SelectList() {

        List<DepartDict> list=departDictMapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public Map SelectPage(int currentPage,int pagesize) {
        IPage<DepartDict> page=new Page<>(currentPage,pagesize);
        page=departDictMapper.selectPage(page,null);
        Map map=new HashMap();
        map.put("Pages",page.getPages());
        map.put("Total",page.getTotal());
        map.put("Records",page.getRecords());
        return map;
    }

    @Override
    public List<DepartDict> SelectDim() {

        List<DepartDict> list=departDictMapper.selectList(queryWrapper.like("depart_id",1));
        return list;
    }

    @Override
    public int delete(int departId) {
        System.out.println(departId);
        int a=departDictMapper.deleteById(departId);
        System.out.println(a);
        return a;
    }

    @Override
    public int addOneDepartDict(DepartDict departDict) {
        int i=departDictMapper.insert(departDict);
        return 0;
    }

    @Override
    public DepartDict UpdateOne(DepartDict departDict) {
        int i=departDictMapper.updateById(departDict);
        return null;
    }


}
