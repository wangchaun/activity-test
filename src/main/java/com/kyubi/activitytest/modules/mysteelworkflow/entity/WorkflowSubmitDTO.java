package com.kyubi.activitytest.modules.mysteelworkflow.entity;

import lombok.Data;


import java.util.Set;

/**
 * @author GLNC-ZLH
 * @date 2020/6/13 9:07
 */
@Data
public class WorkflowSubmitDTO {
    private String remark;

    /**
     * bizType=PROJECT_MODIFY,bizId=inputDetailId
     */
    private Long bizId;
    /**
     * 业务类型
     */
    private String bizType;

    private String flowDefId;


    private String title;

    private Long userId;

    /**
     * 项目负责人id
     */
    private Set<Long> responserIds;

}
