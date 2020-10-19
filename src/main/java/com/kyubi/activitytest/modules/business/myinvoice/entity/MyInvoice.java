package com.kyubi.activitytest.modules.business.myinvoice.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author kyubi
 * @since 2020-10-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MyInvoice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 发票单号
     */
    private String invoiceNo;

    /**
     * 发票状态
     */
    private String invoiceStatus;

    /**
     * 发票信息
     */
    private String invoiceInfo;


    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createPin;

    /**
     * 更新人
     */
    @TableField(fill = FieldFill.INSERT)
    private String updatePin;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date ts;

    /**
     * 是否有效Y有效N无效
     */
    @TableField(fill = FieldFill.INSERT)
    private String yn;

    /**
     * 版本
     */
    @TableField(fill = FieldFill.INSERT)
    private Long version;


}
