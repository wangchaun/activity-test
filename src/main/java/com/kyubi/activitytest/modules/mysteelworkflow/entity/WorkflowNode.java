package com.kyubi.activitytest.modules.mysteelworkflow.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author GLNC-ZLH
 * @date 2020/6/13 9:06
 */
@Data
public class WorkflowNode {
    private Boolean audited;

    private String admin;

    private String id;

    private String key;

    private List<Long> adminIds;

    private String role;

    private Date auditDate;
}
