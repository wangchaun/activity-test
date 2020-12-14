package com.kyubi.activitytest.modules.kyc.model.detail;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @description: Preliminary 类
 * @projectName: mysteel-mbs-service
 * @className: Preliminary
 * @author: wangsiming
 * @createTime: 2020/12/10 9:36
 */
public class Preliminary implements Serializable {


    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;

    /**
     * 客户协议交易的市场 多个以逗号分开 1-Iron Ore、2-Steel、3-Coal、4-Oil Products、5-Petrochemicals、6-Rubber
     */
    @ApiModelProperty(value="客户协议交易的市场 多个以逗号分开 1-Iron Ore、2-Steel、3-Coal、4-Oil Products、5-Petrochemicals、6-Rubber",name="markets",example="")
    private String markets;

    /**
     * 创建人姓名 kyc broker
     */
    @ApiModelProperty(value="创建人姓名（ kyc broker）",name="createName",example="")
    private String createName;


    /**
     * 创建时间 on board date
     */
    @ApiModelProperty(value="创建时间（on board date）",name="createTime",example="")
    private Long createTime;

    /**
     * 客户交易范围，衍生品、实货或者两种都可以
     */
    @ApiModelProperty(value="客户交易范围， 1-衍生品、4-实货、99-两种都可以 ",name="scopeOfTrading",example="")
    private Integer scopeOfTrading;


    /**
     * 指定审核人id
     */
    @ApiModelProperty(value="指定审核人id",name="approverId",example="")
    private Long approverId;


    /**
     * 指定审核人姓名
     */
    @ApiModelProperty(value="指定审核人姓名",name="approverName",example="")
    private String approverName;


    public String getMarkets() {
        return markets;
    }

    public void setMarkets(String markets) {
        this.markets = markets;
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

    public Integer getScopeOfTrading() {
        return scopeOfTrading;
    }

    public void setScopeOfTrading(Integer scopeOfTrading) {
        this.scopeOfTrading = scopeOfTrading;
    }

    public Long getApproverId() {
        return approverId;
    }

    public void setApproverId(Long approverId) {
        this.approverId = approverId;
    }

    public String getApproverName() {
        return approverName;
    }

    public void setApproverName(String approverName) {
        this.approverName = approverName;
    }
}
