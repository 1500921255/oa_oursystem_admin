package com.example.demo.common;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 *  返回结果封装工具类
 * </p>
 *
 * @author lxh
 * @since 2020-09-26
 */

@Data
public class Result implements Serializable {

    private int code; // 200是正常，非200表示异常
    private String msg;
    private Boolean flag;
    private Object data;

    /**
     * 用于返回用户安全数据
     * @param data
     * @return
     */
    protected String SessionID;
    protected Object roles;
    protected Object perms;
    protected Object menus;

    public static Result succ(Object data) {
        return succ(200, "操作成功",true, data);
    }

    public static Result succ(int code, String msg,Boolean flag, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setFlag(true);
        r.setData(data);
        return r;
    }

    public static Result fail(Object data) {
        return fail(400,"操作失败",false, data);
    }

    public static Result fail(String msg, Object data) {
        return fail(400, msg,false, data);
    }

    public static Result fail(int code, String msg,Boolean flag, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setFlag(false);
        r.setData(data);
        return r;
    }


    public static Result succ(String SessionID,Object roles,Object perms,Object menus,int id){
        Result u = new Result();
        u.setCode(200);
        u.setFlag(true);
        u.setMsg("操作成功");
        Map map = new HashMap();
        map.put("SessionID",SessionID);
        map.put("roles",roles);
        map.put("perms",perms);
        map.put("menus",menus);
        map.put("UserId",id);
        u.setData(map);
        return u;
    }
}
