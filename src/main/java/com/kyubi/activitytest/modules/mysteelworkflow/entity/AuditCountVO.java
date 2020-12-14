package com.kyubi.activitytest.modules.mysteelworkflow.entity;

import lombok.Data;

/**
 * @author GLNC-ZLH
 * @date 2020/7/21
 */
@Data
public class AuditCountVO {
    private Long toCount;
    private Long myAuditCount;
    private Long myApplyCount;
}
