package com.kyubi.activitytest.modules.mysteelworkflow.entity;

import lombok.Data;

/**
 * @author GLNC-ZLH
 * @date 2020/6/13 9:27
 */
@Data
public class WorkflowQueryVO {
    private String taskId;

    private String assign;

    private String processDefinitionId;
    private String processInstanceId;
    private String title;
    private String businessKey;
    private String createTime;
    private Long businessId;
}
