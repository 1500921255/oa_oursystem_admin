package com.example.demo.service;

import com.example.demo.entity.DepartDict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lxh
 * @since 2020-09-28
 */
public interface DepartDictService extends IService<DepartDict> {
    List<DepartDict> SelectList();    //查询列表
    Map SelectPage(int currentPage, int pagesize);   //分页查询
    List<DepartDict> SelectDim();    //模糊查询
    int addOneDepartDict(DepartDict departDict); //添加部门
    DepartDict  UpdateOne(DepartDict departDict);//修改部门
    int delete(int departId); //删除部门
}
