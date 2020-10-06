package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.EmployeeUser;
import com.example.demo.mapper.EmployeeUserMapper;
import com.example.demo.service.EmployeeUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.crypto.hash.Md5Hash;
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
 * @since 2020-09-26
 */
@Service
public class EmployeeUserServiceImpl extends ServiceImpl<EmployeeUserMapper, EmployeeUser> implements EmployeeUserService {

    @Autowired
    private EmployeeUserMapper userMapper;

    @Override
    public EmployeeUser LOGIN(String employee_user) {

        EmployeeUser employeeUser = userMapper.selectOne(new QueryWrapper<EmployeeUser>().eq("employee_user",employee_user));
        if(employeeUser == null){
            return null;
        }else {
            return employeeUser;
        }
    }

    @Override
    public Map Pagelist(int current,int size) {
        IPage<EmployeeUser> page = new Page<>(current,size);
        page =  userMapper.selectPage(page,null);
        Map map = new HashMap() ;
        map.put("pages",page.getPages());
        map.put("total",page.getTotal());
        map.put("data",page.getRecords());
        return map;
    }

    @Override
    public int DeleteUser(int employeeId) {
       int i =  userMapper.deleteById(employeeId);
        return i;
    }

    @Override
    public int UpdateUser(EmployeeUser employeeUser) {
        int i = userMapper.updateById(employeeUser);
        return i;
    }

    @Override
    public int InsertUser(EmployeeUser employeeUser) {
        employeeUser.setEmployeePwd(new Md5Hash(employeeUser.getEmployeePwd(),employeeUser.getEmployeeUser(),3).toString());
        int i = userMapper.insert(employeeUser);
        return i;
    }
}
