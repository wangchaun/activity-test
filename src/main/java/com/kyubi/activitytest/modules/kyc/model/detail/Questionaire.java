package com.kyubi.activitytest.modules.kyc.model.detail;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @description: Preliminary 类
 * @projectName: mysteel-mbs-service
 * @className: Preliminary
 * @author: wangsiming
 * @createTime: 2020/12/10 9:36
 */
public class Questionaire implements Serializable {


    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;


    /**
     * 问卷顺序
     */
    @ApiModelProperty(value="问卷顺序",name="quOrder",example="")
    private Short quOrder;


    /**
     * 问卷名称
     */
    @ApiModelProperty(value="问卷名称",name="quName",example="")
    private String quName;

    /**
     * 问卷回复
     */
    @ApiModelProperty(value="问卷回复【多个】",name="answer",example="")
    private List<String> answers;
    /**
     * 是否
     */
    @ApiModelProperty(value="是否 是1 否0",name="yn",example="")
    private Integer yn;
    /**
     * 文件地址
     */
    @ApiModelProperty(value="文件地址",name="fileUrl",example="")
    private String fileUrl;

    public Short getQuOrder() {
        return quOrder;
    }

    public void setQuOrder(Short quOrder) {
        this.quOrder = quOrder;
    }

    public String getQuName() {
        return quName;
    }

    public void setQuName(String quName) {
        this.quName = quName;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
