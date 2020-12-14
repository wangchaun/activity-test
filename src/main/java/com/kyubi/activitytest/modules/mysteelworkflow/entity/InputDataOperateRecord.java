package com.kyubi.activitytest.modules.mysteelworkflow.entity;

import lombok.Data;

/**
 * 调研数据操作记录类
 *
 * @author zhangch
 * @version v2.1.3
 */
@Data
public class InputDataOperateRecord {

    /**
     * Id
     */
    private Integer id;

    /**
     * 任务Id
     */
    private Integer taskId;

    /**
     * 项目Id
     */
    private Integer projectId;

    /**
     * 样本Id
     */
    private Integer sampleId;

    /**
     * 指标Id
     */
    private Integer quotaId;

    /**
     * 指标单位
     */
    private String quotaUnit;

    /**
     * 指标频率 0：日 1：周 2 ：月 3：季 4：年
     */
    private Integer quotaFrequence;

    /**
     * 调研日期
     */
    private String inputDate;

    /**
     * 原调研值
     */
    private String oldInputValue;

    /**
     * 新调研值
     */
    private String newInputValue;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作人id
     */
    private Long operatorId;

    /**
     * 操作时间
     */
    private Long operateTime;

    /**
     * 备注
     */
    private String remark;
    /**
     * 审批人id
     */
    private Long auditorId;
    private String auditor;
    private Integer status;
    private String auditRemark;
    private String oldDataRemark;
    private String newDataRemark;
    private Long auditTime;
    /**
     * 流程实例id
     */
    private String procInstanceId;
    private String projectName;
    private String sampleName;
    private String quotaName;
    /**
     * inputDetail表id
     */
    private Long inputId;

    private Long deptId;
    private String deptName;
}
