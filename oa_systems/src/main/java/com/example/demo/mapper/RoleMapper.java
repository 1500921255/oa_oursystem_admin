package com.example.demo.mapper;

import com.example.demo.entity.Permission;
import com.example.demo.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ymy
 * @since 2020-10-07
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    @Select("select * from permission where id in (select permission_id from role_permission where role_id = #{role_id})")
    Set<Permission> getRolePermissions(int role_id);

}
