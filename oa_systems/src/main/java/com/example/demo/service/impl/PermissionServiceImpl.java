package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.EmployeeUser;
import com.example.demo.entity.Permission;
import com.example.demo.mapper.PermissionMapper;
import com.example.demo.service.PermissionService;
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
 * @since 2020-10-07
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    PermissionMapper permsMapper;

    @Override
    public Map permsList(int current) {
        IPage<Permission> page = new Page<>(current,5);
        page = permsMapper.selectPage(page,null);
        Map map = new HashMap() ;
        map.put("pages",page.getPages());
        map.put("total",page.getTotal());
        map.put("data",page.getRecords());
        return map;
    }

    @Override
    public int permsDelete(int id) {
        int i = permsMapper.deleteById(id);
        return i;
    }

    @Override
    public int permsInsert(Permission perms) {
        int i = permsMapper.insert(perms);
        return i;
    }

    @Override
    public int permsUpdate(Permission perms) {
        int i = permsMapper.updateById(perms);
        return i;
    }

    @Override
    public List<Permission> perms() {
        List<Permission> list = permsMapper.selectList(new QueryWrapper<>());
        return list;
    }



}
