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
public class BasicInformation implements Serializable {


    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;



    /**
     * 申请编号 系统自增,从0001开始往上加
     */
    @ApiModelProperty(value="申请编号",name="applyNo",example="")
    private String applyNo;


    /**
     * 客户编号 客户全称首字母缩写+所选客户类型首字母缩写+所选国家首字母缩写拼接而成
     */
    @ApiModelProperty(value="客户编号",name="clientCode",example="")
    private String clientCode;


    /**
     * 客户全称 唯一 Full Registered Company Name
     */
    @ApiModelProperty(value="客户全称（Full Registered Company Name）",name="clientName",example="")
    private String clientName;


    /**
     * 客户简称 Any Trading Names
     */
    @ApiModelProperty(value="客户简称（Any Trading Names）",name="shortName",example="")
    private String shortName;


    /**
     * 公司注册号 Company Registration No.
     */
    @ApiModelProperty(value="公司注册号（Company Registration No.）",name="registrationNo",example="")
    private String registrationNo;


    /**
     * 注册时间 Incorporation Date
     */
    @ApiModelProperty(value="注册时间（Incorporation Date）",name="registrationTime",example="")
    private Long registrationTime;


    /**
     * 注册国家码 Incorporation Country Code
     */
    @ApiModelProperty(value="注册国家码（Incorporation Country Code）",name="registrationCountryCode",example="")
    private String registrationCountryCode;


    /**
     * 注册国家 Incorporation Country
     */
    @ApiModelProperty(value="注册国家（Incorporation Country）",name="registrationCountry",example="")
    private String registrationCountry;


    /**
     * 注册地址
     */
    @ApiModelProperty(value="注册地址",name="registrationAddress",example="")
    private String registrationAddress;


    /**
     * 公司地址
     */
    @ApiModelProperty(value="公司地址",name="officeAddress",example="")
    private String officeAddress;


    /**
     * 客户的电话号码
     */
    @ApiModelProperty(value="客户的电话号码",name="tel",example="")
    private String tel;


    /**
     * 客户的传真号
     */
    @ApiModelProperty(value="客户的传真号",name="fax",example="")
    private String fax;


    /**
     * 客户的电子邮箱地址
     */
    @ApiModelProperty(value="客户的电子邮箱地址",name="emailAddress",example="")
    private String emailAddress;


    /**
     * 客户类型 1:Bank、2:Domestic Merchant、3:Foreign Merchant、4:Fund、5:Investment Bank、6:Mill Merchant、7:Mine、8:Steel Mill、20:Others；缺省为‘Bank’
     */
    @ApiModelProperty(value="客户类型  1:Bank、2:Domestic Merchant、3:Foreign Merchant、4:Fund、5:Investment Bank、6:Mill Merchant、7:Mine、8:Steel Mill、20:Others；缺省为‘Bank’",name="clientType",example="")
    private Integer clientType;

    /**
     * 客户公司的网页地址
     */
    @ApiModelProperty(value="客户公司的网页地址",name="website",example="")
    private String website;


    /**
     * 客户的主要业务
     */
    @ApiModelProperty(value="客户的主要业务",name="principleBusiness",example="")
    private String principleBusiness;

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public Long getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Long registrationTime) {
        this.registrationTime = registrationTime;
    }

    public String getRegistrationCountryCode() {
        return registrationCountryCode;
    }

    public void setRegistrationCountryCode(String registrationCountryCode) {
        this.registrationCountryCode = registrationCountryCode;
    }

    public String getRegistrationCountry() {
        return registrationCountry;
    }

    public void setRegistrationCountry(String registrationCountry) {
        this.registrationCountry = registrationCountry;
    }

    public String getRegistrationAddress() {
        return registrationAddress;
    }

    public void setRegistrationAddress(String registrationAddress) {
        this.registrationAddress = registrationAddress;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Integer getClientType() {
        return clientType;
    }

    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPrincipleBusiness() {
        return principleBusiness;
    }

    public void setPrincipleBusiness(String principleBusiness) {
        this.principleBusiness = principleBusiness;
    }
}
