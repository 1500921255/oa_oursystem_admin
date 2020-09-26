package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.EmployeeUser;
import com.example.demo.mapper.EmployeeUserMapper;
import com.example.demo.service.EmployeeUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public EmployeeUser LOGIN() {

      EmployeeUser employeeUser = userMapper.selectOne(new QueryWrapper<EmployeeUser>().eq("employee_user","0000"));

        return employeeUser;
    }
}
