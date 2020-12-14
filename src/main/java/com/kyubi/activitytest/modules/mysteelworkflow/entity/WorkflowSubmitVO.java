package com.kyubi.activitytest.modules.mysteelworkflow.entity;

import lombok.Data;

/**
 * @author GLNC-ZLH
 * @date 2020/6/13 9:04
 */
@Data
public class WorkflowSubmitVO {
    private String procInstanceId;

    private String procDefId;
}
