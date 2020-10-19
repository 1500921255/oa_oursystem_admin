package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.Permission;
import com.example.demo.entity.Role;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ymy
 * @since 2020-10-07
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<Role> roleList() {
        List<Role> list = roleMapper.selectList(new QueryWrapper<>());
        return list;
    }

    public int delete(int id){
        int i = roleMapper.deleteById(id);
        return i;
    }

    public int insert(Role role){
        int i = roleMapper.insert(role);
        return i;
    }

    public int update(Role role){
        int i = roleMapper.updateById(role);
        return i;
    }

    @Override
    public Set<Permission> getRolePermissions(int role_id) {
        Set<Permission> set = roleMapper.getRolePermissions(role_id);
        return set;
    }
}
