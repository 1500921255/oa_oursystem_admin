package com.example.demo.service;

import com.example.demo.entity.EmployeeUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Repository;

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
}
