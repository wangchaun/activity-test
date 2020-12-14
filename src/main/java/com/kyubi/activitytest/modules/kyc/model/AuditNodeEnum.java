package com.kyubi.activitytest.modules.kyc.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * @description:审核节点枚举 1-Broker、2-Back Office、3-Compliance、4-Approver、5-Approved、99-Rejected
 * @projectName:mysteel-aliyun-mbs-service_202012021341
 * @className:AuditNodeEnum.java
 * @author: Xiongwl
 * @createTime: 2020年12月4日 上午9:57:09
 */
@ApiModel(value="AuditNodeEnum对象",description="节点VO")
public enum AuditNodeEnum {
    /*
     * Bank
     */
    BROKER("Broker", 1, 1),
    /*
     * Back Office
     */
    BACK_OFFICE("Back Office", 2, 2),
    /*
     * Compliance
     */
    COMPLIANCE("Compliance", 3, 3),
    /*
     * Approver
     */
    APPROVER("Approver", 4, 4),

    ;
    /**
     * 描述
     */
    @ApiModelProperty(value="KYC节点描述",name="desc")
    private String desc;
    /**
     * 枚举值
     */
    @ApiModelProperty(value="KYC节点枚举值",name="value")
    private int value;

	/**
	 * 对应的角色枚举
	 */
	private int roleValue;

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getRoleValue() {
		return roleValue;
	}

	public void setRoleValue(int roleValue) {
		this.roleValue = roleValue;
	}

	AuditNodeEnum(String desc, int value, int roleValue) {
		this.desc = desc;
		this.value = value;
		this.roleValue = roleValue;
	}

	public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

}