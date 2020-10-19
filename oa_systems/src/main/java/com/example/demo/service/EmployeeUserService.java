package com.example.demo.service;

import com.example.demo.entity.EmployeeUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Role;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lxh
 * @since 2020-09-26
 */
public interface EmployeeUserService extends IService<EmployeeUser> {
    EmployeeUser LOGIN(String employee_user);
    Map Pagelist(int current, int size);
    int DeleteUser(int employeeId);
    int UpdateUser(EmployeeUser employeeUser);
    int InsertUser(EmployeeUser employeeUser);
//    @Select("select * from  role where id in(select role_id from employee_user_role where employee_id = #{employee_id})")
    Set<Role> getemployeeRole (int employeeID);
    int[] getemployeeId();
}
