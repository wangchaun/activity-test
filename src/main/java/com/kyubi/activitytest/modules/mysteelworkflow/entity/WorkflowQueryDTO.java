package com.kyubi.activitytest.modules.mysteelworkflow.entity;

import lombok.Data;



/**
 * @author GLNC-ZLH
 * @date 2020/6/13 9:27
 */
@Data
public class WorkflowQueryDTO {


    private Integer pageNum;

    private Integer pageSize;
    private Long userId;
    private String keyword;
    /**
     * bizId为空时查看待审列表
     */
    private Long bizId;
}
