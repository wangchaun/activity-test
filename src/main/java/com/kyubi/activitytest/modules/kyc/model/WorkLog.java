package com.kyubi.activitytest.modules.kyc.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * @description: 审批日志
 * @projectName:mysteel-mbs-service
 * @className:Worklog.java
 * @author: 王毅
 * @createTime: 2020/12/4 9:36
 */
public class WorkLog extends AbstractDTO {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value="日志id",name="id")
    private Long id;

    /**
     * KYC申请id
     */
    @ApiModelProperty(value="KYC申请id",name="kycApplyId")
    private Long kycApplyId;

    /**
     * 操作行为（1、Initiation 2、Submitted 3、PassBack 4、Approved 5、Basic Update 6、Operation Update 7、Rejected'）
     */
    @ApiModelProperty(value="（1、Initiation 2、Submitted 3、PassBack 4、Approved 5、Basic Update 6、Operation Update 7、Rejected'",name="workAction",example="")
    private Integer workAction;

    /**
     * 审核节点 （1-Broker、2-Back Office、3-Compliance、4-Approver、5-Approved、99-Rejected）
     */
    @ApiModelProperty(value="（1-Broker、2-Back Office、3-Compliance、4-Approver、5-Approved、99-Rejected）",name="auditNode",example="")
    private Integer auditNode;

    /**
     * 创建人姓名 kyc broker
     */
    @ApiModelProperty(value="创建人姓名",name="createName",example="")
    private String createName;
    /**
     * 审核备注
     */
    @ApiModelProperty(value="审核备注",name="comment",example="")
    private String comments;


    /**
     * 创建时间 on board date
     */
    @ApiModelProperty(value="创建时间",name="createTime",example="")
    private Long createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKycApplyId() {
        return kycApplyId;
    }

    public void setKycApplyId(Long kycApplyId) {
        this.kycApplyId = kycApplyId;
    }

    public Integer getWorkAction() {
        return workAction;
    }

    public void setWorkAction(Integer workAction) {
        this.workAction = workAction;
    }

    public Integer getAuditNode() {
        return auditNode;
    }

    public void setAuditNode(Integer auditNode) {
        this.auditNode = auditNode;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
