package com.kyubi.activitytest.modules.mysteelworkflow.entity;

import com.sun.istack.internal.NotNull;
import lombok.Data;


/**
 * @author GLNC-ZLH
 * @date 2020/6/30
 */
@Data
public class OperateRecordResubmitDTO {
    @NotNull
    private Long id;
    /**
     * 修改原因
     */
    @NotNull
    private String remark;
    /**
     * 修改调研值
     */
    @NotNull
    private String newInputValue;

    private String title;

    private String operator;

    private Long operatorId;

    private Long projectId;
}
