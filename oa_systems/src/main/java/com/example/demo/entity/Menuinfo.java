package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author ymy
 * @since 2020-10-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Menuinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    private Integer id;

    /**
     * 菜单名
     */
    @TableField("MenuName")
    private String MenuName;

    /**
     * 菜单路由地址
     */
    private String roter;


}
