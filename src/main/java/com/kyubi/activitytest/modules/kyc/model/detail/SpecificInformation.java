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
public class SpecificInformation implements Serializable {


    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;
    /**
     * 与客户商量好的关键佣金率
     */
    @ApiModelProperty(value="客户商量好的关键佣金率",name="clientRates",example="")
    private String clientRates;
    /**
     * 备注
     */
    @ApiModelProperty(value="备注",name="notes",example="")
    private String notes;

    public String getClientRates() {
        return clientRates;
    }

    public void setClientRates(String clientRates) {
        this.clientRates = clientRates;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
