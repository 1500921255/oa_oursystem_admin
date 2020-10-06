package com.example.demo.common;

import lombok.Data;

import java.io.Serializable;

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

    public static Result fail(String msg) {
        return fail(400, msg,false, null);
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

}
