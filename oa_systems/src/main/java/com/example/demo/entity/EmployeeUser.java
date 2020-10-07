package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.annotations.Select;
import org.crazycake.shiro.AuthCachePrincipal;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 
 * </p>
 *
 * @author lxh
 * @since 2020-09-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Component
public class EmployeeUser implements Serializable, AuthCachePrincipal {

    private static final long serialVersionUID = 1L;

    /**
     * 职员ID
     */
    @TableId(value = "employee_id", type = IdType.AUTO)
    private Integer employeeId;

    /**
     * 职员账号
     */
    private String employeeUser;

    /**
     * 职员密码
     */
    private String employeePwd;

    /**
     * 职员姓名
     */
    private String employeeName;

    /**
     * 职员性别
     */
    private String employeeSex;

    /**
     * 职员出生日期
     */
    private String employeeBirth;

    /**
     * 职员电话
     */
    private String employeeTel;

    /**
     * 职员所属部门
     */
    private String employeeDepartId;

    /**
     * 职员状态
     */
    private int employeeStatus;

    /**
     *职员的角色
     */
    @TableField(exist = false)
    private Set<Role> employeeRole = new HashSet<>();

    @Override
    public String getAuthCacheKey() {
        return null;
    }


}
