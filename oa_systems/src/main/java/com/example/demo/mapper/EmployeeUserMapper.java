package com.example.demo.mapper;

import com.example.demo.entity.EmployeeUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Role;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lxh
 * @since 2020-09-26
 */
@Repository
public interface EmployeeUserMapper extends BaseMapper<EmployeeUser> {

//    @Select("select * from  role where id in(select role_id from employee_user_role where employee_id = #{employee_id})")
    Set<Role> getemployeeRole (int employeeID);
}
