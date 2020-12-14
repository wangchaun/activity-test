package com.kyubi.activitytest.modules.mysteelworkflow.entity;

import lombok.Data;


/**
 * @author GLNC-ZLH
 * @date 2020/6/13 9:22
 */
@Data
public class WorkflowExchangeDTO {

    private Integer isPass;
    /**
     * 审批原因
     */
    private String remark;
    /**
     * 审批人
     */
    private Long userId;
    private String userName;
    /**
     * 业务id
     */
    private Long bizId;
}
