package com.example.demo.service;

import com.example.demo.entity.EmployeeUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lxh
 * @since 2020-09-26
 */
public interface EmployeeUserService extends IService<EmployeeUser> {
    EmployeeUser LOGIN(String employee_user,String employee_pwd);
    Map Pagelist(int current, int size);
    int DeleteUser(int employeeId);
    int UpdateUser(EmployeeUser employeeUser);
    int InsertUser(EmployeeUser employeeUser);
}
