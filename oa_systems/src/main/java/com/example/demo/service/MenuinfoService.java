package com.example.demo.service;

import com.example.demo.entity.Menuinfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ymy
 * @since 2020-10-07
 */
public interface MenuinfoService extends IService<Menuinfo> {
    List<Menuinfo> MenuList();
    int DeleteMenu(int id);
    int InsertMenu(Menuinfo menuinfo);
    int UpdateMenu(Menuinfo menuinfo);

}
