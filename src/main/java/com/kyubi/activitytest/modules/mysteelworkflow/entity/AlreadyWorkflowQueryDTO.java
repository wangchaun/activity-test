package com.kyubi.activitytest.modules.mysteelworkflow.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * 已办查询对象
 * @author GLNC-ZLH
 * @date 2020/6/22 10:40
 */
@Data
public class AlreadyWorkflowQueryDTO extends ToWorkflowQueryDTO{
    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    /**
     * 日期字段 auditTime 审核时间, operateTime 申请时间
     */
    private String dateField;

    /**
     * 审核通过和驳回
     */
    private String batchStatus;
}
