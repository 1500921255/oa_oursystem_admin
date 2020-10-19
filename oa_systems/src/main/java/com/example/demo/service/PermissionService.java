package com.example.demo.service;

import com.example.demo.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ymy
 * @since 2020-10-07
 */
public interface PermissionService extends IService<Permission> {

    Map permsList(int current);
    int permsDelete(int id);
    int permsInsert(Permission perms);
    int permsUpdate(Permission perms);
    List<Permission> perms();

}
