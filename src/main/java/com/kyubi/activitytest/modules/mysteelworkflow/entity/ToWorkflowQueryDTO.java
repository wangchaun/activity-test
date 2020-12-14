package com.kyubi.activitytest.modules.mysteelworkflow.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 代办查询对象
 * @author GLNC-ZLH
 * @date 2020/6/22 10:40
 */
@Data
public class ToWorkflowQueryDTO {

    private String projectName;

    private String sampleName;

    private String quotaName;

    private String deptName;
    private Long deptId;
    /**
     * 申请人
     */

    private String operator;
    private String operatorId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date applyStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date applyEnd;

    private Integer pageNum;

    private Integer pageSize;

    private Integer status;
    /**
     * 当前登录人
     */
    private Long userId;
    private Long bizId;

    private String keyword;
}
