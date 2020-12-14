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
public class Agreements implements Serializable {


    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;


    /**
     * 展示顺序
     */
    @ApiModelProperty(value="展示顺序",name="showOrder",example="")
    private Short showOrder;


    /**
     * 展示名称 Documents
     */
    @ApiModelProperty(value="展示名称（Documents）",name="showName",example="")
    private String showName;


    /**
     * 备注 notes
     */
    @ApiModelProperty(value="备注（notes）",name="description",example="")
    private String description;

    /**
     * 附件路径
     */
    @ApiModelProperty(value="附件路径",name="accessoryUrl",example="")
    private String accessoryUrl;

    public Short getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Short showOrder) {
        this.showOrder = showOrder;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAccessoryUrl() {
        return accessoryUrl;
    }

    public void setAccessoryUrl(String accessoryUrl) {
        this.accessoryUrl = accessoryUrl;
    }
}
