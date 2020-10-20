package com.kyubi.activitytest.modules.activiti.model;

import com.kyubi.activitytest.modules.enums.ActivityProcessInstanceEnums;
import com.kyubi.activitytest.modules.enums.ReviewStatusCommonEnum;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: cosmos-poseidon
 * @description:
 * @author: siming.wang
 * @create: 2019-11-05 15:13
 **/
@Data
@Builder
public class ActReviewDto implements Serializable {


    /**
     * 业务单据 ID，用于流程唯一key 反查流程表
     */
    private Long sysNo;
    /**
     * 原始单据ID，用于查找原始表 一般为0L
     */
    private Long parentSysNo;
    /**
     * 审核状态，可以按照共有枚举状态统一管理，由acti理解status自动通过或者驳回
     */
    private String reviewStatus;
    /**
     * 审核编号
     */
    private String reviewNo;
    private ActivityProcessInstanceEnums actProcessInstanceEnums;
    private String userName;
    /**
     * 备注
     */
    private String comment;
    /**
     * 权限
     */
    protected String permission;
    /**
     * 公用审核枚举
     */
    private ReviewStatusCommonEnum reviewStatusCommonEnum;
    /**
     * 驳回原因
     */
    private String reasonMsg;
}
