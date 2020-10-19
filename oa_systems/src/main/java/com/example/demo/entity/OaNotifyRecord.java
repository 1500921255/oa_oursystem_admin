package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 通知通告发送记录
 * </p>
 *
 * @author ymy
 * @since 2020-10-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Component
public class OaNotifyRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 缂栧彿
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 闁氨鐓￠柅姘啞ID
     */
    private Integer oaNotifyId;

    /**
     * 闂備浇顫夋禍浠嬪磿鏉堫偁浜归柛顐ゅ枔椤?
     */
    private Integer employeeId;

    /**
     * 闂冨懓顕伴弽鍥唶
     */
    private String readFlag;

    /**
     * 阅读时间
     */
    private LocalDate readDate;

    @TableField(exist = false)
    private List<OaNotice> OaNotice = new ArrayList<>();
    @TableField(exist = false)
    private List<EmployeeUser> EmployeeUser = new ArrayList<>();
}
