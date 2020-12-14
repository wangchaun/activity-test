package com.kyubi.activitytest.modules.mysteelworkflow.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 我发起的审批查询对象
 * @author GLNC-ZLH
 * @date 2020/6/22 10:40
 */
@Data
public class MyWorkflowQueryDTO {
    
    private String projectName;

    private String sampleName;

    private String quotaName;

    private Integer status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date applyStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date applyEnd;

    private Integer pageNum;

    private Integer pageSize;

    private String batchStatus;
}
