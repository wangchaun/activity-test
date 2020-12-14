package com.kyubi.activitytest.modules.kyc.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @description: WorkFlowShowVo 类
 * @projectName: mysteel-mbs-service
 * @className: WorkFlowShowVo
 * @author: wangsiming
 * @createTime: 2020/12/14 10:58
 */
@ApiModel(value="WorkFlowShowVo对象",description="SHOW明细VO")
public class WorkFlowShowVo implements Serializable {
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;

    /**
     * 当前节点 1-Broker、2-Back Office、3-Compliance、4-Approver
     */
    @ApiModelProperty(value="节点信息",name="auditNodeEnum")
    private String auditNode;

    /**
     * 颜色: 绿色:1 红色:99 灰色:0 默认0灰色
     */
    @ApiModelProperty(value="颜色(绿色:1 红色:99 灰色:0 默认0灰色)",name="color")
    private Integer color = GREY;
    /**
     * 创建人姓名 kyc broker
     */
    @ApiModelProperty(value="创建人姓名",name="createName",example="")
    private String createName;
    /**
     * 审核备注
     */
    @ApiModelProperty(value="审核备注",name="comment",example="")
    private String comment;


    /**
     * 创建时间 on board date
     */
    @ApiModelProperty(value="创建时间",name="createTime",example="")
    private Long createTime;


    /**
     * 常量池
     */
    public final static int GREEN = 1;
    public final static int RED = 99;
    public final static int GREY = 0;

    public String getAuditNode() {
        return auditNode;
    }

    public void setAuditNode(String auditNode) {
        this.auditNode = auditNode;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
