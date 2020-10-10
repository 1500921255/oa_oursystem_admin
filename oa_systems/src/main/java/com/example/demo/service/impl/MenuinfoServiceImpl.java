package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.Menuinfo;
import com.example.demo.mapper.MenuinfoMapper;
import com.example.demo.service.MenuinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ymy
 * @since 2020-10-07
 */
@Service
public class MenuinfoServiceImpl extends ServiceImpl<MenuinfoMapper, Menuinfo> implements MenuinfoService {

    @Autowired
    MenuinfoMapper menuinfoMapper;

    @Override
    public List<Menuinfo> MenuList() {
        List<Menuinfo> list = menuinfoMapper.selectList(new QueryWrapper<>());
        return list;
    }

    @Override
    public int DeleteMenu(int id) {
        int i = menuinfoMapper.deleteById(id);
        return i;
    }

    @Override
    public int InsertMenu(Menuinfo menuinfo) {
        int i = menuinfoMapper.insert(menuinfo);
        return i;
    }

    @Override
    public int UpdateMenu(Menuinfo menuinfo) {
        int i = menuinfoMapper.updateById(menuinfo);
        return i;
    }
}
