package com.example.demo.service;

import com.example.demo.entity.Permission;
import com.example.demo.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ymy
 * @since 2020-10-07
 */
public interface RoleService extends IService<Role> {
    List<Role> roleList();
    int delete(int id);
    int insert(Role role);
    int update(Role role);

    @Select("select * from permission where id in (select permission_id from role_permission where role_id = #{role_id})")
    Set<Permission> getRolePermissions(int role_id);

}
